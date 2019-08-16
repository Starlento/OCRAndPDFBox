package util;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageTree;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

public class PDFUtil {

    public static void PDFtoImage(String file, String imgFilePath) throws IOException {
        PDDocument document = PDDocument.load(new File(file));
        PDFRenderer renderer = new PDFRenderer(document);
        int pageCount = document.getNumberOfPages();
        outputImg(imgFilePath, renderer, pageCount);
        document.close();
    }

    public static void getTextFromPDFArea(String file, Rectangle rect, String outputPath, String separator)
            throws IOException
    {
        PDDocument document = PDDocument.load(new File(file));
        /* take rect's height==0 and width==0 request as asking for full page */
        if (rect.height == 0 && rect.width == 0) {
            extractFullPage(outputPath, separator, document);
        }
        else{
            extractFromArea(rect, outputPath, separator, document);
        }
    }

    public static void extractImgFromPDF(String path) throws IOException {

        File file = new File(path);

        if (path.endsWith(".pdf")) {
            PDDocument document = PDDocument.load(file);
            int pageSize = document.getNumberOfPages();
            outputImg(document, pageSize);
        }
    }

    private static void extractFromArea(Rectangle rect, String outputPath, String separator, PDDocument document) throws IOException {
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(false);
        stripper.setWordSeparator(separator);
        //(x坐标，y坐标，长，宽)
        stripper.addRegion("class1", rect);
//        List allPages = document.getDocumentCatalog().getAllPages()
        PDPageTree allPages = document.getPages();
        PDPage firstPage = allPages.get(0);
        stripper.extractRegions(firstPage);
        /* output the string */
        String text = stripper.getTextForRegion("class1");
        FileUtil.outputTXT(text, outputPath);
        document.close();
    }

    private static void extractFullPage(String outputPath, String separator, PDDocument document) throws IOException {
        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setSortByPosition(false);
        stripper.setWordSeparator(separator);
        String text = stripper.getText(document);
        FileUtil.outputTXT(text,outputPath);
        document.close();
    }

    private static void outputImg(String imgFilePath, PDFRenderer renderer, int pageCount) throws IOException {
        for (int i = 0; i < pageCount; i++) {

            BufferedImage image = renderer.renderImageWithDPI(i, 600);

//            BufferedImage image = renderer.renderImage(i, 1.0f);

            ImageIO.write(image, "PNG",
                    new File(imgFilePath + "img_" + i + ".png"));
        }
    }

    private static void outputImg(PDDocument document, int pageSize) throws IOException {
        for (int i = 0; i < pageSize; i++) {
            PDPage page = document.getPage(i);
            PDResources resources = page.getResources();
            Iterable<COSName> cosNames = resources.getXObjectNames();
            if (cosNames != null) {
                for (COSName cosName : cosNames) {
                    if (resources.isImageXObject(cosName)) {
                        PDImageXObject Ipdmage = (PDImageXObject) resources.getXObject(cosName);
                        BufferedImage image = Ipdmage.getImage();
                        FileOutputStream out = new FileOutputStream("D:\\" + UUID.randomUUID() + ".png");
                        ImageIO.write(image, "png", out);
                        out.close();
                    }
                }
            }
        }
    }
}
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

    public static void GetTextFromPDFArea(String file, Rectangle rect, String outputPath)
            throws IOException
//            , CryptographyException
    {
        PDDocument document = PDDocument.load(new File(file));

//        if (document.isEncrypted()) {
//            document.decrypt("");
//        }

        /* Get text from appointed area */
        PDFTextStripperByArea stripper = new PDFTextStripperByArea();
        stripper.setSortByPosition(true);
        //(x坐标，y坐标，长，宽)
        stripper.addRegion("class1", rect);

//        List allPages = document.getDocumentCatalog().getAllPages();

        PDPageTree allPages = document.getPages();
        PDPage firstPage = allPages.get(0);
        stripper.extractRegions(firstPage);

//        System.out.println( "Text in the area:" + rect );
//        System.out.println( stripper.getTextForRegion( "class1" ) );

        document.close();
        /* output the string */
        FileUtil.doSTOutput(stripper.getTextForRegion("class1"),outputPath);
    }

    public static void extractImgFromPDF(String path) throws IOException {

        File file = new File(path);

        if (path.endsWith(".pdf")) {
            PDDocument document = PDDocument.load(file);
            int pageSize = document.getNumberOfPages();
            outputText(document, pageSize);
            outputImg(document, pageSize);
        }
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
            // 图片内容
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

    private static void outputText(PDDocument document, int pageSize) throws IOException {
        for (int i = 0; i < pageSize; i++) {
            // 文本内容
            PDFTextStripper stripper = new PDFTextStripper();
            // 设置按顺序输出
            stripper.setSortByPosition(true);
            stripper.setStartPage(i + 1);
            stripper.setEndPage(i + 1);
            String text = stripper.getText(document);
            System.out.println(text.trim());
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-");
        }
    }
}
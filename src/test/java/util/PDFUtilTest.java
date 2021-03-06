package util;

import junit.framework.TestCase;

import java.awt.*;
import java.io.IOException;

public class PDFUtilTest extends TestCase {

    private static String[] pdfPath = {
            "D:\\IPSProject\\RTM E-Invoice\\OOCL Debit.pdf",
            "D:\\IPSProject\\RTM E-Invoice\\INV_ORIGINAL_5135031199.pdf",
            "D:\\IPSProject\\RTM E-Invoice\\1.pdf"
    };

    public void test_PDFtoImage() throws IOException {
        /* given */
        String file = pdfPath[0];
        String imgFilePath = "D:\\IPSProject\\RTM E-Invoice\\output\\";
        /* when */
        PDFUtil.PDFtoImage(file, imgFilePath);
        /* then */

    }

    public void test_GetTextFromPDFArea() throws IOException {
        /* given */
        String file = pdfPath[1];
        String outputPath = "D:\\IPSProject\\RTM E-Invoice\\output\\output.txt";
        Rectangle rect = new Rectangle(0, 0, 0, 0);
        String separator = "❤";
        /* when */
        PDFUtil.getTextFromPDFArea(file, rect, outputPath, separator);
        /* then */

    }

    public void test_ExtractImgFromPDF() throws IOException {
        /* given */
        String file = pdfPath[2];
        /* when */
        PDFUtil.extractImgFromPDF(file);
        /* then */

    }
}
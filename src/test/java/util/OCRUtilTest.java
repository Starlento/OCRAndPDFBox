package util;

import junit.framework.TestCase;
import net.sourceforge.tess4j.TesseractException;

import java.io.IOException;

public class OCRUtilTest extends TestCase {

    private static String[] imgPath = {
            "D:\\IPSProject\\RTM E-Invoice\\output\\img_0.png",
            "D:\\IPSProject\\RTM E-Invoice\\output\\img_1.png"
    };
    private static String outputPath = "D:\\IPSProject\\RTM E-Invoice\\output\\output.txt";

    @Deprecated
    public void test_OCRImg() throws Exception {

        /* given */
        String path = imgPath[0];
        /* when */
        OCRUtil.OCRImg(path, outputPath);
        /* then */

    }

    public void test_OCRAll() throws IOException, TesseractException {

        /* given */
        String path = imgPath[0];
        /* when */
        OCRUtil.OCRAll(path, outputPath);
        /* then */

    }
}
package util;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import service.OcrRecognizer;

import java.io.File;
import java.io.IOException;

public class OCRUtil {

    private static String TESSERACT = "C:\\Users\\huangst3\\AppData\\Local\\Tesseract-OCR\\tessdata";

    public static void OCRAll(String path, String outputPath) throws IOException, TesseractException {

        ITesseract instance = new Tesseract();

//        instance.setTessVariable("tessedit_write_images", "true");

        instance.setDatapath(TESSERACT);
        String result = instance.doOCR(new File(path));

//            System.out.println(result);

        FileUtil.outputTXT(result, outputPath);
    }

    @Deprecated
    public static void OCRImg(String path, String outputPath) throws Exception {
        System.out.println("ORC Test Begin......");
        String result = new OcrRecognizer().recognizeText(new File(path), "png");
        /* output */
        FileUtil.outputTXT(result, outputPath);
        System.out.println("ORC Test End......");
    }
}

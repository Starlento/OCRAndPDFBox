package util;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtil {

    public static void outputTXT(String result, String outputPath) throws IOException {

        File output = new File(outputPath);
        System.out.println("OutPut Path:" + output.getPath());
        FileOutputStream fos1 = new FileOutputStream(output);
        OutputStreamWriter dos1 = new OutputStreamWriter(fos1);
        dos1.write(result);
        dos1.close();
    }

    public static void outputTXT(PDDocument document, int pageSize) throws IOException {
        for (int i = 0; i < pageSize; i++) {
            // 文本内容
            PDFTextStripper stripper = new PDFTextStripper();
            // 设置按顺序输出
            stripper.setSortByPosition(true);
            stripper.setStartPage(i + 1);
            stripper.setEndPage(i + 1);
            String text = stripper.getText(document);
            //for testing
            System.out.println(text.trim());
            System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-");
        }
    }
}

package org.example.pdfbox.pagesize;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;

import java.io.IOException;

public class pagesize {

    /** user space units per inch */
    private static final float POINTS_PER_INCH = 72;

    /** user space units per millimeter */
    private static final float POINTS_PER_MM = 1 / (10 * 2.54f) * POINTS_PER_INCH;

    public static void main(String[] args) throws IOException {

        String filename = "target/pagesize.pdf";
        PDDocument doc = new PDDocument();
//        PDPage page = new PDPage(PDRectangle.A4);
        // 生成 a4 长度一半的 pdf
        PDPage page = new PDPage(new PDRectangle(210 * POINTS_PER_MM, 297 / 2 * POINTS_PER_MM));
        doc.addPage(page);

        doc.save(filename);
    }
}

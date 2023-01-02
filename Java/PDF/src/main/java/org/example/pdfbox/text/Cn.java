package org.example.pdfbox.text;

import org.apache.fontbox.ttf.OTFParser;
import org.apache.fontbox.ttf.OpenTypeFont;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import java.io.File;
import java.io.IOException;

/**
 * 思源黑体简中 ttf
 * https://github.com/adobe-fonts/source-han-sans/tree/release#downloading-source-han-sans
 */
public class Cn {

    public static void main(String[] args) throws IOException {

        String filename = "target/A5Pdf.pdf";
        String message = "A5Pdf，简体中文，繁體中文。";
        String fontFile = "src/main/resources/ttf/SourceHanSansSC-VF.ttf";

        new File(filename).delete();
        try (PDDocument doc = new PDDocument();) {
            PDPage page = new PDPage();
            doc.addPage(page);


//            PDFont font = PDType0Font.load(doc, new File(fontFile));
//            PDFont font = PDType0Font.load(doc, new File(fontFile));

            PDFont font = PDType0Font.load(doc, new File(fontFile));
            PDPageContentStream contents1 = new PDPageContentStream(doc, page);
            contents1.beginText();
            contents1.setFont(font, 12);
            contents1.newLineAtOffset(100, 700);
            contents1.showText(message);
            contents1.endText();
            contents1.close();


            PDPageContentStream contents2 = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.APPEND, false);
            contents2.beginText();
            contents2.setFont(font, 12);
            contents2.newLineAtOffset(100, 500);
            contents2.showText(message);
            contents2.endText();
            contents2.close();

            doc.save(filename);
        }


    }


}

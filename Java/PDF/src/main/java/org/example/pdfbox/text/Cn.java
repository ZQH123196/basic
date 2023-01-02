package org.example.pdfbox.comprehension;

import org.apache.fontbox.ttf.OTFParser;
import org.apache.fontbox.ttf.OpenTypeFont;
import org.apache.fontbox.ttf.TTFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.File;
import java.io.IOException;

public class A5Pdf {

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
            OTFParser parser = new OTFParser(false, true);
            OpenTypeFont otf = parser.parse(fontFile);

            PDFont font = PDType0Font.load(doc, new File(fontFile));
            PDPageContentStream contents = new PDPageContentStream(doc, page);
            contents.beginText();
            contents.setFont(font, 12);
            contents.newLineAtOffset(100, 700);
            contents.showText(message);
            contents.endText();
            contents.close();
            doc.save(filename);
        }


    }


}

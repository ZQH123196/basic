package org.example.pdfbox.comprehension;

import lombok.extern.slf4j.Slf4j;
import org.apache.fontbox.ttf.OTFParser;
import org.apache.fontbox.ttf.OpenTypeFont;
import org.apache.fontbox.ttf.TTFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Slf4j
public class A5Pdf {

    public static void main(String[] args) throws IOException {

        String outPDFPath = "target/A5Pdf.pdf";
        String message = "A5Pdf，简体中文，繁體中文。";
        String fontFile = "src/main/resources/ttf/SourceHanSansSC-VF.ttf";

//        String templatePDFPath = "src/main/resources/form/A5Pdf_form_template.pdf";
        String templatePDFPath = "target/NewSimpleForm.pdf";

        new File(outPDFPath).delete();
        try (PDDocument doc = PDDocument.load(new File(templatePDFPath))) {
            PDFont font = PDType0Font.load(doc, new File(fontFile));


            PDAcroForm acroForm = doc.getDocumentCatalog().getAcroForm();

            log.info("------------");
            for (PDField field : acroForm.getFields()) {
                log.info(field.toString());
            }
            log.info("------------");

            PDTextField textBox = (PDTextField) acroForm.getField("SampleField");
            String format = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
            textBox.setValue(format);



            doc.save(outPDFPath);
        }

    }


}

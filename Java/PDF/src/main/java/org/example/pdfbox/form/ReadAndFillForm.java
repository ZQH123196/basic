package org.example.pdfbox.form;

import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.apache.pdfbox.rendering.PDFRenderer;

import java.io.File;
import java.io.IOException;

@Slf4j
public class ReadAndFillForm {


    public static void main(String[] args) throws IOException {
        try (PDDocument doc = PDDocument.load(new File("src/main/resources/form/SimpleForm.pdf"));) {

            new PDFRenderer(doc).renderImage(0);
            PDAcroForm acroForm = doc.getDocumentCatalog().getAcroForm();
            log.info("------------");
            for (PDField field : acroForm.getFields()) {
                log.info(field.toString());
            }
            log.info("------------");

            PDTextField textBox = (PDTextField) acroForm.getField("SampleField");
            String value = textBox.getValue();
            log.info(value);


            textBox.setValue("new content!");

            doc.save("target/ReadAndFillForm.pdf");

        }

    }
}

/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.example.pdfbox.form;

import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
import org.apache.pdfbox.pdmodel.interactive.annotation.*;
import org.apache.pdfbox.pdmodel.interactive.form.*;
import org.apache.pdfbox.rendering.PDFRenderer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Test of some the form examples.
 *
 * @author Tilman Hausherr
 */
public class TestCreateSimpleForms
{
    public TestCreateSimpleForms()
    {
    }

    /**
     * Test of CreateSimpleForm
     *
     * @throws IOException
     */
    @Test
    public void testCreateSimpleForm() throws IOException
    {
        CreateSimpleForm.main(null);
        PDDocument doc = PDDocument.load(new File("target/SimpleForm.pdf"));
        new PDFRenderer(doc).renderImage(0);
        PDAcroForm acroForm = doc.getDocumentCatalog().getAcroForm();
        PDTextField textBox = (PDTextField) acroForm.getField("SampleField");
        Assertions.assertEquals("Sample field content", textBox.getValue());
        String format = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
        textBox.setValue(format);

        PDFont font = getFontFromWidgetResources(textBox, "Helv");

//        Assertions.assertEquals("Helvetica", font.getName());
//        Assertions.assertTrue(font.isStandard14());
        doc.save("target/NewSimpleForm.pdf");
        doc.close();
    }


    private PDFont getFontFromWidgetResources(PDTextField textBox, String fontResourceName) throws IOException
    {
        PDAnnotationWidget widget = textBox.getWidgets().get(0);
        PDAppearanceDictionary appearance = widget.getAppearance();
        PDAppearanceEntry normalAppearance = appearance.getNormalAppearance();
        PDAppearanceStream appearanceStream = normalAppearance.getAppearanceStream();
        PDResources resources = appearanceStream.getResources();
        return resources.getFont(COSName.getPDFName(fontResourceName));
    }
}

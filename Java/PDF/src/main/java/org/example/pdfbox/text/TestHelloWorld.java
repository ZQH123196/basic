package org.example.pdfbox.text;

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

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 *
 * @author Tilman Hausherr
 */
public class TestHelloWorld
{
    private static final String OUTPUT_DIR = "target/test-output";

    @BeforeAll
    public static void init() throws Exception
    {
        new File(OUTPUT_DIR).mkdirs();
    }

    @Test
    public void testHelloWorldTTF() throws IOException
    {
        String outputFile = OUTPUT_DIR + "/HelloWorldTTF.pdf";
        String message = "HelloWorldTTF.pdf";
        String fontFile = "src/main/resources/ttf/LiberationSans-Regular.ttf";

        new File(outputFile).delete();

        String[] args = new String[] { outputFile, message, fontFile };
        HelloWorldTTF.main(args);

        checkOutputFile(outputFile, message);

    }

    @Test
    public void testHelloWorld() throws IOException
    {
        String outputDir = "target/test-output";
        String outputFile = outputDir + "/HelloWorld.pdf";
        String message = "HelloWorld.pdf";

        new File(outputFile).delete();

        String[] args = new String[] { outputFile, message };
        HelloWorld.main(args);

        checkOutputFile(outputFile, message);

    }

    private void checkOutputFile(String outputFile, String message) throws IOException
    {
        PDDocument doc = PDDocument.load(new File(outputFile));
        PDFTextStripper stripper = new PDFTextStripper();
        String extractedText = stripper.getText(doc).trim();
        assertEquals(message, extractedText);
        doc.close();
    }
}

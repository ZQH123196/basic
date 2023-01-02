package org.example.pdfbox.images;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

public class TestAddImageToPDF {

    @Test
    public void test() throws IOException {

        String inputFile = "target/test-output/HelloWorld.pdf";
        String imagePath = "src/main/resources/images/nh_sign.png";
        String outputFile = "target/test-output/TestAddImageToPDF.pdf";
        new File(outputFile).delete();
        String[] args = {inputFile, imagePath, outputFile};
        AddImageToPDF.main(args);
    }
}

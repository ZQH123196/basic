package org.eor.designPattern.visitor.ToolApplicationV2;


import java.util.ArrayList;
import java.util.List;



/**
 * @author ZQH12
 */
public class ToolApplication {
    public static void main(String[] args) {
        List<ResourceFile> resourceFileList = new ArrayList<>();
        resourceFileList.add(new PdfFile("a.pdf"));
        resourceFileList.add(new PptFile("b.ppt"));
        resourceFileList.add(new WordFile("c.word"));

        Extractor extractor = new Extractor();
        for (ResourceFile resourceFile : resourceFileList) {
            String res = extractor.extract2txt(resourceFile);
            System.out.println(res);
        }
    }
}
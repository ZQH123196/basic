package org.eor.designPattern.visitor.ToolApplicationV1;


import java.util.ArrayList;
import java.util.List;

// 运行结果是：
// Extract PDF.
// Extract WORD.
// Extract PPT.
public class ToolApplication {
    public static void main(String[] args) {
        List<ResourceFile> resourceFileList = new ArrayList<>();
        resourceFileList.add(new PdfFile("a.pdf"));
        resourceFileList.add(new PptFile("b.ppt"));
        resourceFileList.add(new WordFile("c.word"));

        for (ResourceFile resourceFile : resourceFileList) {
            String res = resourceFile.extract2txt();
            System.out.println(res);
        }
    }
}
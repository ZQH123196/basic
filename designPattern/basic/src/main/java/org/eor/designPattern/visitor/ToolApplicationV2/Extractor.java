package org.eor.designPattern.visitor.ToolApplicationV2;

public class Extractor {

    public String extract2txt(ResourceFile resourceFile) {
//        throw new UnsupportedOperationException("error! 此函数仅用于规避函数重载静态编译和多态运行时动态解析的问题！");
        return "into extract2txt(ResourceFile resourceFile)，Extract by " + resourceFile.getClass().getSimpleName();
    }

    // 以下三个函数永远不会被运行
    public static String extract2txt(PdfFile pdfFile) {
        return "Extract by " + pdfFile.getClass().getSimpleName();
    }
    public static String extract2txt(PptFile pptFile) {
        return "Extract by " + pptFile.getClass().getSimpleName();
    }
    public static String extract2txt(WordFile wordFile) {
        return "Extract by " + wordFile.getClass().getSimpleName();
    }

}

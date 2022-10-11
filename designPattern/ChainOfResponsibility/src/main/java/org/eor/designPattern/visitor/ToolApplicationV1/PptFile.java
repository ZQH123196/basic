package org.eor.designPattern.visitor.ToolApplicationV1;

public class PdfFile extends ResourceFile {
    @Override
    public String extract2txt(String filePath) {
        return this.getClass().getName();
    }
}

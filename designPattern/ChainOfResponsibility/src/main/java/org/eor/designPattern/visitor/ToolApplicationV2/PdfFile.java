package org.eor.designPattern.visitor.ToolApplicationV2.ToolApplicationV1;

public class PdfFile extends ResourceFile {
    public PdfFile(String filePath) {
        super(filePath);
    }

    @Override
    public String extract2txt() {
        return "Extract by " + this.getClass().getSimpleName();
    }
}

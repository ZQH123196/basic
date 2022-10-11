package org.eor.designPattern.visitor.ToolApplicationV2.ToolApplicationV1;

public class PptFile extends ResourceFile {
    public PptFile(String filePath) {
        super(filePath);
    }

    @Override
    public String extract2txt() {
        return "Extract by " + this.getClass().getSimpleName();
    }
}

package org.eor.designPattern.visitor.ToolApplicationV1;

public class WordFile extends ResourceFile {
    public WordFile(String filePath) {
        super(filePath);
    }

    @Override
    public String extract2txt() {
        return "Extract by " + this.getClass().getSimpleName();
    }
}

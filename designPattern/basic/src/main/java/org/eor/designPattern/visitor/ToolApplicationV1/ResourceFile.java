package org.eor.designPattern.visitor.ToolApplicationV1;

public abstract class ResourceFile {
    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    public abstract String extract2txt();
}

package org.eor.designPattern.visitor.ToolApplicationV2;

public abstract class ResourceFile {
    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

}

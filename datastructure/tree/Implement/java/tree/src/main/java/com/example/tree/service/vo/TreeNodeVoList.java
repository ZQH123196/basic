package com.example.tree.service.vo;

import com.example.tree.dao.entity.TreeNode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class TreeNodeVoList {

    @JsonProperty("nodeName")
    private String nodeName;

    @JsonProperty("nodePath")
    private String nodePath;

    @JsonProperty("children")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeNodeVoList> children;

    public TreeNodeVoList(TreeNode treeNode) {
        this.nodeName = treeNode.getNodeName();
        this.nodePath = treeNode.getPathString();
    }

    public TreeNodeVoList(String nodeName, String nodePath, List<TreeNodeVoList> children) {
        this.nodeName = nodeName;
        this.nodePath = nodePath;
        this.children = children;
    }

    public void setChildren(List<TreeNodeVoList> children) {
        this.children = children;
    }

    public List<TreeNodeVoList> getChildren() {
        return children;
    }

    public void addChildren(TreeNode treeNode) {
        this.children.add(new TreeNodeVoList(treeNode));
    }

    public static TreeNodeVoBuilder builder() {
        return new TreeNodeVoBuilder();
    }




    public static class TreeNodeVoBuilder {
        private String nodeName;
        private String nodePath;
        private List<TreeNodeVoList> children;

        TreeNodeVoBuilder() {
        }

        public TreeNodeVoBuilder nodeName(String nodeName) {
            this.nodeName = nodeName;
            return this;
        }

        public TreeNodeVoBuilder nodePath(String nodePath) {
            this.nodePath = nodePath;
            return this;
        }

        public TreeNodeVoBuilder children(List<TreeNodeVoList> children) {
            this.children = children;
            return this;
        }

        public TreeNodeVoList build() {
            return new TreeNodeVoList(nodeName, nodePath, children);
        }

        public String toString() {
            return "TreeNodeVo.TreeNodeVoBuilder(nodeName=" + this.nodeName + ", nodePath=" + this.nodePath + ", children=" + this.children + ")";
        }
    }
}

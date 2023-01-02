package com.example.tree.service.vo;

import com.example.tree.dao.entity.TreeNode;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Map;

@Getter
public class TreeNodeVoMap {

    @JsonProperty("nodeName")
    private String nodeName;

    @JsonProperty("nodePath")
    private String nodePath;

    @JsonProperty("children")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Map<String, TreeNodeVoMap> children;

    public TreeNodeVoMap(TreeNode treeNode) {
        this.nodeName = treeNode.getNodeName();
        this.nodePath = treeNode.getPathString();
    }

    public TreeNodeVoMap(String nodeName, String nodePath, Map<String, TreeNodeVoMap> children) {
        this.nodeName = nodeName;
        this.nodePath = nodePath;
        this.children = children;
    }

    public void setChildren(Map<String, TreeNodeVoMap> children) {
        this.children = children;
    }

    public Map<String, TreeNodeVoMap> getChildren() {
        return children;
    }

    public void addChildren(TreeNode treeNode) {
        this.children.put(treeNode.getNodeName(), new TreeNodeVoMap(treeNode));
    }

    public static TreeNodeVoBuilder builder() {
        return new TreeNodeVoBuilder();
    }




    public static class TreeNodeVoBuilder {
        private String nodeName;
        private String nodePath;
        private Map<String, TreeNodeVoMap> children;

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

        public TreeNodeVoBuilder children(Map<String, TreeNodeVoMap> children) {
            this.children = children;
            return this;
        }

        public TreeNodeVoMap build() {
            return new TreeNodeVoMap(nodeName, nodePath, children);
        }

        public String toString() {
            return "TreeNodeVo.TreeNodeVoBuilder(nodeName=" + this.nodeName + ", nodePath=" + this.nodePath + ", children=" + this.children + ")";
        }
    }
}

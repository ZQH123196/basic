package com.example.tree.service.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class MultipleTreeMap {

    /**
     * 每一个 key 都代表一棵树的 root
     */
    @JsonProperty("MultipleTreeMap")
    private Map<String, TreeNodeVo> treeNodeVoMap;

    MultipleTreeMap(Map<String, TreeNodeVo> treeNodeVoMap) {
        this.treeNodeVoMap = treeNodeVoMap;
    }

    public static MultipleTreeBuilder builder() {
        return new MultipleTreeBuilder();
    }


    public static class MultipleTreeBuilder {
        private Map<String, TreeNodeVo> treeNodeVoMap;

        MultipleTreeBuilder() {
        }

        public MultipleTreeBuilder treeNodeVoMap(Map<String, TreeNodeVo> treeNodeVoMap) {
            this.treeNodeVoMap = treeNodeVoMap;
            return this;
        }

        public MultipleTreeMap build() {
            return new MultipleTreeMap(treeNodeVoMap);
        }

        public String toString() {
            return "MultipleTree.MultipleTreeBuilder(treeNodeVoMap=" + this.treeNodeVoMap + ")";
        }
    }
}

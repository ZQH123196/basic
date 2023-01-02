package com.example.tree.service.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * 对比 list 版本，仅仅只是将 nodeName 作为 key 而已，
 * 之所以会有 map 结构是为了高性能，因为使用 list 的话在扫描完全部之前，是无法立即定位到目标节点的。
 * 但不要过早优化，map 结构表达起来比较困难，应当优先使用 list 结构。
 * 结构形如：
 * {
 *     "MultipleTreeMap": {
 *         "A": {
 *             "nodeName": "A",
 *             "nodePath": "A",
 *             "children": {
 *                 "B": {
 *                     "nodeName": "B",
 *                     "nodePath": "AB"
 *                 },
 *                 "C": {
 *                     "nodeName": "C",
 *                     "nodePath": "AC",
 *                     "children": {
 *                         "D": {
 *                             "nodeName": "D",
 *                             "nodePath": "ACD"
 *                         },
 *                         "E": {
 *                             "nodeName": "E",
 *                             "nodePath": "ACE"
 *                         },
 *                         "F": {
 *                             "nodeName": "F",
 *                             "nodePath": "ACF",
 *                             "children": {
 *                                 "G": {
 *                                     "nodeName": "G",
 *                                     "nodePath": "ACFG"
 *                                 }
 *                             }
 *                         }
 *                     }
 *                 }
 *             }
 *         }
 *     }
 * }
 */
public class MultipleTreeMap {

    /**
     * 每一个 key 都代表一棵树的 root
     */
    @JsonProperty("MultipleTreeMap")
    private Map<String, TreeNodeVoMap> treeNodeVoMap;

    MultipleTreeMap(Map<String, TreeNodeVoMap> treeNodeVoMap) {
        this.treeNodeVoMap = treeNodeVoMap;
    }

    public static MultipleTreeBuilder builder() {
        return new MultipleTreeBuilder();
    }


    public static class MultipleTreeBuilder {
        private Map<String, TreeNodeVoMap> treeNodeVoMap;

        MultipleTreeBuilder() {
        }

        public MultipleTreeBuilder treeNodeVoMap(Map<String, TreeNodeVoMap> treeNodeVoMap) {
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

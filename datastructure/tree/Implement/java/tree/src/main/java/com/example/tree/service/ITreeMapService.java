package com.example.tree.service;

import com.example.tree.dao.entity.TreeNode;
import com.example.tree.service.vo.MultipleTreeMap;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface ITreeMapService {

    /**
     * 获取所有树
     */
    void getMultipleTree();

    /**
     * 获取目标节点的所有子树，也可用于获取一棵树的全部
     *
     * @return
     */
    MultipleTreeMap getTargetNodeChildren(String nodeName);

    /**
     * 根据根节点获取树
     * 是 getTargetNodeChildren 的一个限定版函数
     *
     * @return
     */
    MultipleTreeMap getTreeByRoot(String rootName);

    /**
     * 获取一颗树的任意某一层级
     *
     * @return
     */
    List<TreeNode> getLayerTree(String rootNode, int layer);


    /**
     * 查找孤立节点，一般用于清理无效节点
     * @param rootNode
     * @param layer
     */
    void findIsolatedNode(String rootNode, int layer);


    /**
     * 查找循环节点，避免死循环
     * @param rootNode
     * @param layer
     */
    void findLoopNode(String rootNode, int layer);


}

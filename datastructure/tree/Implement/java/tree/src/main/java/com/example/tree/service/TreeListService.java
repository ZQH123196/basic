package com.example.tree.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.tree.dao.entity.TreeNode;
import com.example.tree.dao.service.ITreeNodeService;
import com.example.tree.service.vo.MultipleTreeList;
import com.example.tree.service.vo.TreeNodeVoList;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class TreeListService implements ITreeListService {


    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TreeListService.class);


    static String pathEnumLabel = "path_string";
    static String lenPath = String.format("LENGTH(%s)", pathEnumLabel);


    @Resource
    ITreeNodeService treeNodeService;

    /**
     * 获取所有树，当节点路径为 1 时，视为一棵树的根节点
     *
     * @return
     */
    @Override
    public MultipleTreeList getMultipleTree() {
        QueryWrapper<TreeNode> wrapper = Wrappers.<TreeNode>query()
                .eq(lenPath, 1);
        List<TreeNode> rootList = treeNodeService.list(wrapper);


        List<TreeNodeVoList> treeNodeVoMapList = new ArrayList<>();
        for (TreeNode treeNode : rootList) {
            TreeNodeVoList treeNodeList = getTreeNodeVoList(treeNode.getNodeName());
            treeNodeVoMapList.add(treeNodeList);
        }
        MultipleTreeList multipleTreeList = MultipleTreeList.builder().treeNodeVoList(treeNodeVoMapList).build();
        return multipleTreeList;
    }

    /**
     * 获取 目标的所有子树
     * 当节点路径为 1 时，视为一棵树的根节点
     *
     * @return
     */
    @Override
    public MultipleTreeList getTargetNodeChildren(String nodeName) {
        List<TreeNodeVoList> newTreeNodeList = new ArrayList<>();
        newTreeNodeList.add(getTreeNodeVoList(nodeName));

        MultipleTreeList multipleTreeList = MultipleTreeList.builder().treeNodeVoList(newTreeNodeList).build();

        return multipleTreeList;
    }


    @Override
    public MultipleTreeList getTreeByRoot(String rootName) {
        return getTargetNodeChildren(rootName);
    }


    /**
     * 获取一颗树的任意某一层级
     * SET @RootNode := 'A'
     * SET @Layer := 1
     * SELECT * FROM tree_node WHERE path_string LIKE '%'+@RootNode+'%'
     * AND LENGTH(path_string) = @Layer
     */
    @Override
    public List<TreeNode> getLayerTree(String rootNode, int layer) {
        QueryWrapper<TreeNode> wrapper = Wrappers.<TreeNode>query()
                .like(pathEnumLabel, rootNode)
                .eq(lenPath, layer);
        List<TreeNode> curLayerNodes = treeNodeService.list(wrapper);

        return curLayerNodes;
    }

    @Override
    public void findIsolatedNode(String rootNode, int layer) {

    }

    @Override
    public void findLoopNode(String rootNode, int layer) {

    }


    private TreeNodeVoList toTreeNodeVo(List<TreeNode> treeNodeList) {

        TreeNode rootNode = treeNodeList.get(0);
        treeNodeList.remove(0);
        TreeNodeVoList rootNodeVo = new TreeNodeVoList(rootNode);

        List<TreeNodeVoList> childNode = getChildNode(rootNodeVo, treeNodeList);
        rootNodeVo.setChildren(childNode);
        recursionSetChildWithEffect(rootNodeVo, treeNodeList);

        return rootNodeVo;
    }

    private static List<TreeNodeVoList> getChildNode(TreeNodeVoList targetNode, List<TreeNode> treeNodeList) {
        // 路径枚举中包含这个值并且这个值是倒数第二位，就说明是有父子关系
        List<TreeNodeVoList> childNodeList = new ArrayList<>();
        for (TreeNode treeNode : treeNodeList) {
            String nodeName = treeNode.getNodeName();
            String pathString = treeNode.getPathString();
            if (pathString.substring(pathString.length() - 2, pathString.length() - 1).equals(targetNode.getNodeName())) {
                childNodeList.add(TreeNodeVoList.builder().nodeName(nodeName).nodePath(pathString).build());
            }
        }
        return childNodeList;
    }

    private static void recursionSetChildWithEffect(TreeNodeVoList targetNode, List<TreeNode> treeNodeList) {
        List<TreeNodeVoList> childNode = getChildNode(targetNode, treeNodeList);
        targetNode.setChildren(childNode);
        for (TreeNodeVoList treeNodeVoList : childNode) {
            recursionSetChildWithEffect(treeNodeVoList, treeNodeList);
        }
    }

    private TreeNodeVoList getTreeNodeVoList(String nodeName) {
        QueryWrapper<TreeNode> wrapper = Wrappers.<TreeNode>query()
                .like(pathEnumLabel, nodeName)
                .orderByAsc(lenPath);
        List<TreeNode> treeNodeList = treeNodeService.list(wrapper);

        TreeNodeVoList treeNodeVoList = toTreeNodeVo(treeNodeList);


        return treeNodeVoList;
    }


}

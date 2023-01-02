package com.example.tree.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.tree.dao.entity.TreeNode;
import com.example.tree.dao.service.ITreeNodeService;
import com.example.tree.service.vo.MultipleTreeMap;
import com.example.tree.service.vo.TreeNodeVoMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class TreeMapService implements ITreeMapService {


    private static final Logger log = org.slf4j.LoggerFactory.getLogger(TreeMapService.class);
    @Resource
    ObjectMapper objectMapper;


    static String pathEnumLabel = "path_string";
    static String lenPath = String.format("LENGTH(%s)", pathEnumLabel);


    @Resource
    ITreeNodeService treeNodeService;

    /**
     * 获取所有树，当节点路径为 1 时，视为一棵树的根节点
     */
    @Override
    public void getMultipleTree() {
        QueryWrapper<TreeNode> wrapper = Wrappers.<TreeNode>query()
                .eq(lenPath, 1);
        List<TreeNode> rootList = treeNodeService.list(wrapper);

        log.info("{}", rootList);


        for (TreeNode treeNode : rootList) {

        }
    }

    /**
     * 获取 目标的所有子树
     * 当节点路径为 1 时，视为一棵树的根节点
     *
     * @return
     */
    @Override
    public MultipleTreeMap getTargetNodeChildren(String nodeName) throws JsonProcessingException {
        QueryWrapper<TreeNode> wrapper = Wrappers.<TreeNode>query()
                .like(pathEnumLabel, nodeName)
                .orderByAsc(lenPath);
        List<TreeNode> treeNodeList = treeNodeService.list(wrapper);

        TreeNodeVoMap treeNodeVoMap = toTreeNodeVo(treeNodeList);

        HashMap<String, TreeNodeVoMap> nodeVoMap = new HashMap<>();
        nodeVoMap.put(treeNodeVoMap.getNodeName(), treeNodeVoMap);

        MultipleTreeMap multipleTreeMap = MultipleTreeMap.builder().treeNodeVoMap(nodeVoMap).build();

        return multipleTreeMap;
    }

    @Override
    public MultipleTreeMap getTreeByRoot(String rootName) throws JsonProcessingException {
        return getTargetNodeChildren(rootName);
    }


    /**
     * 获取一颗树的任意某一层级
     * SET @RootNode := 'A'
     * SET @Layer := 1
     * SELECT * FROM tree_node WHERE path_string LIKE '%'+@RootNode+'%'
     * AND LENGTH(path_string) = @Layer
     *
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


    private TreeNodeVoMap toTreeNodeVo(List<TreeNode> treeNodeList) {

        TreeNode rootNode = treeNodeList.get(0);
        treeNodeList.remove(0);
        TreeNodeVoMap rootNodeVo = new TreeNodeVoMap(rootNode);

        // 转成 kv 结构的 map，nodename 做 key
        Map<String, String> treeNodeMap = treeNodeList.stream().collect(Collectors.toMap(TreeNode::getNodeName, TreeNode::getPathString));


        Map<String, TreeNodeVoMap> childNode = getChildNode(rootNodeVo, treeNodeMap);
        rootNodeVo.setChildren(childNode);
        recursionSetChildWithEffect(rootNodeVo, treeNodeMap);

        return rootNodeVo;
    }

    private static void recursionSetChildWithEffect(TreeNodeVoMap targetNode, Map<String, String> treeNodeMap) {
        Map<String, TreeNodeVoMap> childNode = getChildNode(targetNode, treeNodeMap);
        targetNode.setChildren(childNode);
        for (String key : childNode.keySet()) {
            recursionSetChildWithEffect(childNode.get(key), treeNodeMap);
        }
    }

    private static Map<String, TreeNodeVoMap> getChildNode(TreeNodeVoMap targetNode, Map<String, String> nodeMap) {
        // 路径枚举中包含这个值并且这个值是倒数第二位，就说明是有父子关系
        Map<String, TreeNodeVoMap> childNode = new HashMap<>();
        nodeMap.forEach((nodeName, pathStr) -> {
            if (pathStr.substring(pathStr.length() - 2, pathStr.length() - 1).equals(targetNode.getNodeName())) {
                childNode.put(nodeName, TreeNodeVoMap.builder().nodeName(nodeName).nodePath(pathStr).build());
            }
        });
        return childNode;
    }


}

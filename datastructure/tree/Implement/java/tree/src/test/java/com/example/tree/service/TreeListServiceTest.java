package com.example.tree.service;


import com.example.tree.dao.entity.TreeNode;
import com.example.tree.service.vo.MultipleTreeList;
import com.example.tree.service.vo.MultipleTreeMap;
import com.example.tree.service.vo.TreeNodeVoList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class TreeListServiceTest {

    @Resource
    ObjectMapper objectMapper;

    @Resource
    ITreeListService treeListService;


    @Test
    void jacksonList() throws JsonProcessingException {
        List<TreeNodeVoList> treeNodeVoMapList = new ArrayList<>();
        treeNodeVoMapList.add(TreeNodeVoList.builder().nodeName("A").nodePath("A").build());
        treeNodeVoMapList.add(TreeNodeVoList.builder().nodeName("B").nodePath("AB").build());

        MultipleTreeList multipleTreeList = MultipleTreeList.builder().treeNodeVoList(treeNodeVoMapList).build();


        String jsonStr = objectMapper.writeValueAsString(multipleTreeList);

        log.info(jsonStr);
    }

    @Test
    void getMultipleTree() throws JsonProcessingException {
        MultipleTreeList multipleTree = treeListService.getMultipleTree();

        String jsonStr = objectMapper.writeValueAsString(multipleTree);

        log.info(jsonStr);
    }

    @Test
    void getTargetNodeChildren() {
    }

    @ParameterizedTest
    @ValueSource(strings = {"A"})
    void getTreeByRoot(String rootName) throws JsonProcessingException {
        MultipleTreeList multipleTree = treeListService.getTreeByRoot(rootName);
        String jsonStr = objectMapper.writeValueAsString(multipleTree);

        log.info(jsonStr);
    }

    @ParameterizedTest
    @CsvSource(value = {"A, 1", "A, 2", "A, 3"})
    void getLayerTree(String rootNode, int layer) {
        List<TreeNode> curLayerNodes = treeListService.getLayerTree(rootNode, layer);

        for (TreeNode curLayerNode : curLayerNodes) {
            log.info("nodeName = {}, path = {}", curLayerNode.getNodeName(), curLayerNode.getPathString());
        }
    }

    @Test
    void findIsolatedNode() {
    }

    @Test
    void findLoopNode() {
    }
}
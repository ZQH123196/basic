package com.example.tree.service;

import com.example.tree.dao.entity.TreeNode;
import com.example.tree.service.vo.MultipleTreeMap;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.List;


@SpringBootTest
@Slf4j
class TreeMapServiceTest {


    @Resource
    ObjectMapper objectMapper;

    @Resource
    ITreeMapService treeMapService;


    @Test
    void getAllTree() {

    }

    @ParameterizedTest
    @ValueSource(strings = {"A"})
    void getTreeByRoot(String rootName) throws JsonProcessingException {
        MultipleTreeMap multipleTree = treeMapService.getTreeByRoot(rootName);
        String jsonStr = objectMapper.writeValueAsString(multipleTree);

        log.info(jsonStr);
    }

    @ParameterizedTest
    @CsvSource(value = {"A, 1", "A, 2", "A, 3"})
    void getLayerTree(String rootNode, int layer) {
        List<TreeNode> curLayerNodes = treeMapService.getLayerTree(rootNode, layer);

        for (TreeNode curLayerNode : curLayerNodes) {
            log.info("nodeName = {}, path = {}", curLayerNode.getNodeName(), curLayerNode.getPathString());
        }
    }
}
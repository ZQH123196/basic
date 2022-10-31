package com.example.rbac0withhierarchy.service;

import com.example.rbac0withhierarchy.dao.service.ServiceCommon;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Slf4j
class ServiceCommonTest {

    @Resource
    ServiceCommon serviceCommon;

    @Test
    void getAllRoletHierarchyInfo() {
        Map<String, Map> allRoletHierarchyInfo = serviceCommon.getAllRoletHierarchyInfo();

        log.info("allRoletHierarchyInfo={}", allRoletHierarchyInfo);


    }
}
package com.example.rbac1.controller.crud;


import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 父子权限表，parent_role_id 为 null 就代表是根节点，程序只允许管理员的 parent_role_id 为 null。 前端控制器
 * </p>
 *
 * @author zqh
 * @since 2022-10-31
 */
@RestController
@RequestMapping("/rbac1/role-hierarchy")
public class RoleHierarchyController {

}

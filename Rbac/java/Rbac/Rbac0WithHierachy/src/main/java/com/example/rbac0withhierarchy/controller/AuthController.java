package com.example.rbac0withhierarchy.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.rbac0withhierarchy.dao.entity.*;
import com.example.rbac0withhierarchy.dao.mapper.PermissionMapper;
import com.example.rbac0withhierarchy.dao.mapper.RoleHierarchyMapper;
import com.example.rbac0withhierarchy.dao.service.ServiceCommon;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;

@RestController
@RequestMapping("Auth")
public class AuthController {

    @Resource
    ObjectMapper objectMapper;

    @Resource
    ServiceCommon serviceCommon;

    @Resource
    PermissionMapper permissionMapper;


    @Resource
    RoleHierarchyMapper roleHierarchyMapper;


    @PostMapping("login")
    public String login(@RequestHeader String username, @RequestHeader String password) throws JsonProcessingException {
        User user = new User().selectById(username);
        if (!user.getPassword().equals(password)) {
            return "账号或密码出错！";
        }
        return objectMapper.writeValueAsString(user);
    }

    /**
     * 根据角色来进行判断
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("doSomeByRoleAdmin")
    public String doSomeByRoleAdmin(@RequestHeader String username, @RequestHeader String password) {
        List<RoleUser> roleUsers = serviceCommon.getRoleUsers(username);
        for (RoleUser roleUser : roleUsers) {
            if (roleUser.getRole().equals("admin")) {
                return "操作成功！";
            }
        }
        return "没有权限！";
    }


    /**
     * 根据是否有权限（解析权限表达式）来判断
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("addRoleByPerm")
    public String addRoleByPerm(@RequestHeader String username, @RequestHeader String password) {
        List<RoleUser> roleUsers = serviceCommon.getRoleUsers(username);

        String needPermEl = "system:role:create";
        LambdaQueryWrapper<Permission> wrapper = Wrappers.<Permission>lambdaQuery().eq(Permission::getExpression, needPermEl);
        Permission permission = permissionMapper.selectOne(wrapper);
        if (permission == null) {
            return String.format("数据库并无权限[%s]的定义！", needPermEl);
        }

        HashMap<RoleUser, List<String>> rolePermMap = serviceCommon.getRolePerm(roleUsers);
        for (Map.Entry<RoleUser, List<String>> roleKey : rolePermMap.entrySet()) {
            if (rolePermMap.get(roleKey.getKey()).contains(needPermEl)) {
                return "操作成功！";
            }
        }
        return "没有权限！";
    }


    @PostMapping("getAllRoletHierarchyInfo")
    public Map<String, Map> getAllRoletHierarchyInfo() {
        return serviceCommon.getAllRoletHierarchyInfo();
    }


    /**
     * 获取当前角色父子级的继承关系
     *
     * @param username
     * @param password
     * @return
     */
    @PostMapping("getCurRoletHierarchyInfo")
    public String getCurRoletHierarchyInfo(@RequestHeader String username, @RequestHeader String password) {

        // 获取角色用户关联关系
        List<RoleUser> roleUsers = serviceCommon.getRoleUsers(username);
        // 遍历每个


        return null;
    }

    @PostMapping("addRoleWithParent")
    public String addRoleWithParent(@RequestHeader String username, @RequestHeader String password) {


//        roleHierarchyMapper

        return null;
    }

}

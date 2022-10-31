package com.example.rbac1.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.rbac1.dao.entity.*;
import com.example.rbac1.dao.mapper.PermissionMapper;
import com.example.rbac1.dao.mapper.RoleHierarchyMapper;
import com.example.rbac1.dao.mapper.RolePermissionMapper;
import com.example.rbac1.dao.mapper.RoleUserMapper;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ServiceCommonImpl {
    @Resource
    RoleUserMapper roleUserMapper;
    @Resource
    RolePermissionMapper rolePermissionMapper;
    @Resource
    PermissionMapper permissionMapper;

    @Resource
    RoleHierarchyMapper roleHierarchyMapper;

    public Map<String, Map> getAllRoletHierarchyInfo() {

        LambdaQueryWrapper<RoleHierarchy> wrapper = Wrappers.lambdaQuery();
        wrapper.isNull(RoleHierarchy::getParentRoleId);

        // 得到所有根节点
        List<RoleHierarchy> roots = roleHierarchyMapper.selectList(wrapper);
        Map<String, Map> hierarchyMap = new LinkedHashMap<>();
        for (RoleHierarchy root : roots) {
            getTreeByRecursion(root, hierarchyMap);

        }
        return hierarchyMap;
    }

    public void getTreeByRecursion(RoleHierarchy hierarchy, Map hierarchyMap) {
        LambdaQueryWrapper<RoleHierarchy> wrapperL1 = Wrappers.lambdaQuery();
        wrapperL1.eq(RoleHierarchy::getParentRoleId, hierarchy.getRole());
        List<RoleHierarchy> childList = roleHierarchyMapper.selectList(wrapperL1);
        if (childList.size() == 0) {
            return;
        } else {
            Map<String, Map<Object, Object>> childHierarchyMap = childList.stream().map(v -> v.getRole()).collect(Collectors.toMap(r -> r, v -> new HashMap<>()));
            hierarchyMap.put(hierarchy.getRole(), childHierarchyMap);
            for (RoleHierarchy roleHierarchy : childList) {
                getTreeByRecursion(roleHierarchy, childHierarchyMap);
            }
        }

    }


    public List<RoleUser> getRoleUsers(String username) {
        User user = new User().selectById(username);
        LambdaQueryWrapper<RoleUser> wrapper = Wrappers.<RoleUser>lambdaQuery()
                .eq(RoleUser::getUsername, user.getUsername());
        List<RoleUser> roleUsers = roleUserMapper.selectList(wrapper);
        return roleUsers;
    }

    public HashMap<RoleUser, List<String>> getRolePerm(List<RoleUser> roleUsers) {
        HashMap<RoleUser, List<String>> rolePermMap = Maps.newHashMap();
        for (RoleUser roleUser : roleUsers) {
            LambdaQueryWrapper<RolePermission> wrapper = Wrappers.lambdaQuery();
            wrapper.eq(RolePermission::getRole, roleUser.getRole());
            List<RolePermission> rolePermissionList = rolePermissionMapper.selectList(wrapper);
            for (RolePermission rolePermission : rolePermissionList) {
                LambdaQueryWrapper<Permission> wrapperPerm = Wrappers.lambdaQuery();
                wrapperPerm.eq(Permission::getId, rolePermission.getPermissionId());
                List<Permission> permissionList = permissionMapper.selectList(wrapperPerm);
                List<String> permElList = permissionList
                        .stream()
                        .map((p) -> {
                            return p.getExpression();
                        })
                        .collect(Collectors.toList());
                rolePermMap.put(roleUser, permElList);
            }
        }
        return rolePermMap;
    }

    public HashMap<String, List<Permission>> getRolePerm(String roleName) {
        HashMap<String, List<Permission>> rolePermMap = Maps.newHashMap();
        LambdaQueryWrapper<RolePermission> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RolePermission::getRole, roleName);
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectList(wrapper);
        for (RolePermission rolePermission : rolePermissionList) {
            LambdaQueryWrapper<Permission> wrapperPerm = Wrappers.lambdaQuery();
            wrapperPerm.eq(Permission::getId, rolePermission.getPermissionId());
            List<Permission> permissionList = permissionMapper.selectList(wrapperPerm);
            rolePermMap.put(roleName, permissionList);
        }
        return rolePermMap;
    }
}

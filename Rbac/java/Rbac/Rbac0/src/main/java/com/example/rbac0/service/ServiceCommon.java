package com.example.rbac0.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.rbac0.dao.entity.*;
import com.example.rbac0.dao.mapper.PermissionMapper;
import com.example.rbac0.dao.mapper.RolePermissionMapper;
import com.example.rbac0.dao.mapper.RoleUserMapper;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceCommon {
    @Resource
    RoleUserMapper roleUserMapper;
    @Resource
    RolePermissionMapper rolePermissionMapper;
    @Resource
    PermissionMapper permissionMapper;

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

package com.example.rbac1.service.impl;

import com.example.rbac1.dao.service.RoleGroupAdminService;
import com.example.rbac1.dao.service.RoleService;
import com.example.rbac1.dao.service.RoleUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

@Service
public class AuthServiceImpl {

    @Resource
    RoleGroupAdminService roleGroupAdminService;

    @Resource
    RoleService roleService;

    @Resource
    ServiceCommonImpl serviceCommon;

    @Resource
    RoleUserService roleUserService;


    /**
     * 在有 group 的概念下：
     * 先查询用户所持有的角色，再查询用户所属 角色组 跟 用户组 的角色，然后堆到放到 set 中去重
     * @param username
     * @return
     */
    public Set<String> getCurUserRoles(String username) {
        Set<String> roles = roleUserService.getRolesByUserName(username);
        Set<String> rolesByGroup = roleGroupAdminService.getRoleByUsername(username);
        roles.addAll(rolesByGroup);
        return roles;
    }
}

package com.example.rbac0withgroup.service.impl;

import com.example.rbac0withgroup.dao.entity.Role;
import com.example.rbac0withgroup.dao.entity.RoleGroupAdmin;
import com.example.rbac0withgroup.dao.entity.RoleUser;
import com.example.rbac0withgroup.dao.entity.User;
import com.example.rbac0withgroup.dao.service.RoleGroupAdminService;
import com.example.rbac0withgroup.dao.service.RoleService;
import com.example.rbac0withgroup.dao.service.RoleUserService;
import com.example.rbac0withgroup.dao.service.UserService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@SpringBootTest
@Slf4j
class AuthServiceImplTest {

    @Resource
    AuthServiceImpl authService;

    @Resource
    RoleService roleService;

    @Resource
    RoleUserService roleUserService;

    @Resource
    UserService userService;

    @Resource
    RoleGroupAdminService roleGroupAdminService;


    String username = "AuthServiceImplTest_User1";

    @BeforeEach
    void setUp() {
        User user = new User();
        user.setUsername(username);
        user.setPassword("123");
        userService.save(user);

        Role role1 = new Role();
        role1.setRole("AuthServiceImplTest_Role1");
        roleService.save(role1);


        RoleUser roleUser = new RoleUser();
        roleUser.setRole(role1.getRole());
        roleUser.setUsername(user.getUsername());
        roleUserService.save(roleUser);


        // 下面对 group 进行初始化
        Role role2 = new Role();
        role2.setRole("AuthServiceImplTest_Role2");
        roleService.save(role2);

        RoleGroupAdmin roleGroupAdmin = new RoleGroupAdmin();
        String groupName = "AuthServiceImplTest_group";
        roleGroupAdmin.setGroup(groupName);

        String sepStr = "!@@!";
        // 将 1 跟 2 都加进去，理论上去重之后得到的 roles 只有 两个
        ArrayList<String> roles = Lists.newArrayList(role1.getRole(), role2.getRole());
        roleGroupAdmin.setRoles(roles);


        List<String> users = Lists.newArrayList(new String[]{user.getUsername()});
        roleGroupAdmin.setUsers(users);
        roleGroupAdminService.save(roleGroupAdmin);
    }

    @Test
    void getCurUserRole() {

        Set<String> curUserRoles = authService.getCurUserRoles(username);
        log.info("curUserRoles={}", curUserRoles);
        Assertions.assertTrue(curUserRoles.size() == 2);
    }
}
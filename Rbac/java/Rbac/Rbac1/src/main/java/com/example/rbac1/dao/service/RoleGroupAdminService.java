package com.example.rbac1.dao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rbac1.dao.entity.RoleGroupAdmin;

import java.util.Set;

public interface RoleGroupAdminService extends IService<RoleGroupAdmin> {
    Set<String> getRoleByUsername(String username);
}

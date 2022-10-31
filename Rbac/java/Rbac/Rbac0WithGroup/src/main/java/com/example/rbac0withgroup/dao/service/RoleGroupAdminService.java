package com.example.rbac0withgroup.dao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rbac0withgroup.dao.entity.RoleGroupAdmin;
import com.example.rbac0withgroup.dao.mapper.RoleGroupAdminMapper;

import java.util.Set;

public interface RoleGroupAdminService extends IService<RoleGroupAdmin> {
    Set<String> getRoleByUsername(String username);
}

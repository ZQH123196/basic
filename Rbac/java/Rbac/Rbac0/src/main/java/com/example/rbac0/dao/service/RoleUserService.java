package com.example.rbac0.dao.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rbac0.dao.entity.RoleUser;

import java.util.Set;

public interface RoleUserService extends IService<RoleUser> {
    Set<String> getRolesByUserName(String username);
}

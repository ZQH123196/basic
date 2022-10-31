package com.example.rbac0.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rbac0.dao.entity.RolePermission;
import com.example.rbac0.dao.mapper.RolePermissionMapper;
import com.example.rbac0.dao.service.RolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionImplService extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
}

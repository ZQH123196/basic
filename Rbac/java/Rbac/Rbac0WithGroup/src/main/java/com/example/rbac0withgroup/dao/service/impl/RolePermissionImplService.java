package com.example.rbac0withgroup.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rbac0withgroup.dao.entity.RolePermission;
import com.example.rbac0withgroup.dao.mapper.RolePermissionMapper;
import com.example.rbac0withgroup.dao.service.RolePermissionService;
import org.springframework.stereotype.Service;

@Service
public class RolePermissionImplService extends ServiceImpl<RolePermissionMapper, RolePermission> implements RolePermissionService {
}

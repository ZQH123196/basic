package com.example.rbac0withgroup.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rbac0withgroup.dao.entity.Role;
import com.example.rbac0withgroup.dao.mapper.RoleMapper;
import com.example.rbac0withgroup.dao.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

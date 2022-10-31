package com.example.rbac0.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rbac0.dao.entity.Role;
import com.example.rbac0.dao.mapper.RoleMapper;
import com.example.rbac0.dao.service.RoleService;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}

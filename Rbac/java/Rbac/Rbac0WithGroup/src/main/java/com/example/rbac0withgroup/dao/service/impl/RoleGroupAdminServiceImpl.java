package com.example.rbac0withgroup.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rbac0withgroup.dao.entity.RoleGroupAdmin;
import com.example.rbac0withgroup.dao.mapper.RoleGroupAdminMapper;
import com.example.rbac0withgroup.dao.service.RoleGroupAdminService;
import com.google.common.collect.Sets;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleGroupAdminServiceImpl extends ServiceImpl<RoleGroupAdminMapper, RoleGroupAdmin> implements RoleGroupAdminService {



    @Override
    public Set<String> getRoleByUsername(String username) {
        LambdaQueryWrapper<RoleGroupAdmin> wrapper = Wrappers.lambdaQuery();
        wrapper.like(RoleGroupAdmin::getUsers, username);
        HashSet<String> roleSets = Sets.newHashSet();
        for (RoleGroupAdmin roleGroupAdmin : list(wrapper)) {
            roleSets.addAll(roleGroupAdmin.getRoles());
        }

        return roleSets;
    }


}

package com.example.rbac1.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rbac1.dao.entity.RoleGroupAdmin;
import com.example.rbac1.dao.mapper.RoleGroupAdminMapper;
import com.example.rbac1.dao.service.RoleGroupAdminService;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

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

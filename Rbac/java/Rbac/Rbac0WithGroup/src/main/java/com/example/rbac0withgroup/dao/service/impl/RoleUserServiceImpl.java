package com.example.rbac0withgroup.dao.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rbac0withgroup.dao.entity.RoleUser;
import com.example.rbac0withgroup.dao.mapper.RoleUserMapper;
import com.example.rbac0withgroup.dao.service.RoleService;
import com.example.rbac0withgroup.dao.service.RoleUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService {


    @Resource
    RoleService roleService;

    @Override
    public Set<String> getRolesByUserName(String username) {
        LambdaQueryWrapper<RoleUser> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(RoleUser::getUsername, username);
        List<RoleUser> roleUserList = list(wrapper);
        Set<String> roleSets = roleUserList.stream().map(v -> v.getRole()).collect(Collectors.toSet());
        return roleSets;
    }



}

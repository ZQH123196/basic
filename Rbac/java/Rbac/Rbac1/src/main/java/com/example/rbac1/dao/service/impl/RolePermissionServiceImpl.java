package com.example.rbac1.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rbac1.dao.entity.RolePermission;
import com.example.rbac1.dao.mapper.RolePermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色权限关联表 服务实现类
 * </p>
 *
 * @author zqh
 * @since 2022-10-31
 */
@Service
public class RolePermissionServiceImpl extends ServiceImpl<RolePermissionMapper, RolePermission> implements IService<RolePermission> {

}

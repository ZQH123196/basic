package com.example.rbac1.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rbac1.dao.entity.Permission;
import com.example.rbac1.dao.mapper.PermissionMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author zqh
 * @since 2022-10-31
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IService<Permission> {

}

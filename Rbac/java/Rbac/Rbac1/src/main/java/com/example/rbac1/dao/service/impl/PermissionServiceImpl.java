package com.example.mpgenerator.d.rbac1.service.impl;

import com.example.mpgenerator.d.rbac1.entity.Permission;
import com.example.mpgenerator.d.rbac1.mapper.PermissionMapper;
import com.example.mpgenerator.d.rbac1.service.IPermissionService;
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
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}

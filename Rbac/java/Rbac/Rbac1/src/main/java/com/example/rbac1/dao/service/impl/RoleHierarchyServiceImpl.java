package com.example.mpgenerator.d.rbac1.service.impl;

import com.example.mpgenerator.d.rbac1.entity.RoleHierarchy;
import com.example.mpgenerator.d.rbac1.mapper.RoleHierarchyMapper;
import com.example.mpgenerator.d.rbac1.service.IRoleHierarchyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 父子权限表，parent_role_id 为 null 就代表是根节点，程序只允许管理员的 parent_role_id 为 null。 服务实现类
 * </p>
 *
 * @author zqh
 * @since 2022-10-31
 */
@Service
public class RoleHierarchyServiceImpl extends ServiceImpl<RoleHierarchyMapper, RoleHierarchy> implements IRoleHierarchyService {

}

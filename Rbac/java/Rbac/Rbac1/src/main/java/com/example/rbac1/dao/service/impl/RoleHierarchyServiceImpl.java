package com.example.rbac1.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rbac1.dao.entity.RoleHierarchy;
import com.example.rbac1.dao.mapper.RoleHierarchyMapper;
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
public class RoleHierarchyServiceImpl extends ServiceImpl<RoleHierarchyMapper, RoleHierarchy> implements IService<RoleHierarchy> {

}

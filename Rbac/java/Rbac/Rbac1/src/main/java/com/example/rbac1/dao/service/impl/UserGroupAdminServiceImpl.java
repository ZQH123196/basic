package com.example.rbac1.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.rbac1.dao.entity.UserGroupAdmin;
import com.example.rbac1.dao.mapper.UserGroupAdminMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户组表 服务实现类
 * </p>
 *
 * @author zqh
 * @since 2022-10-31
 */
@Service
public class UserGroupAdminServiceImpl extends ServiceImpl<UserGroupAdminMapper, UserGroupAdmin> implements IService<UserGroupAdmin> {

}

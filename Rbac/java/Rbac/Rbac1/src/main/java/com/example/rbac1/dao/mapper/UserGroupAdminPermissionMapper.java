package com.example.rbac1.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rbac1.dao.entity.UserGroupAdmin;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface UserGroupAdminPermissionMapper extends BaseMapper<UserGroupAdmin> {
}

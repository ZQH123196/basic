package com.example.rbac0withgroup.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rbac0withgroup.dao.entity.Permission;
import com.example.rbac0withgroup.dao.entity.UserGroupAdmin;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface UserGroupAdminPermissionMapper extends BaseMapper<UserGroupAdmin> {
}

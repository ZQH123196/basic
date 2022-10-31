package com.example.rbac0withhierarchy.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rbac0withhierarchy.dao.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface PermissionMapper extends BaseMapper<Permission> {
}

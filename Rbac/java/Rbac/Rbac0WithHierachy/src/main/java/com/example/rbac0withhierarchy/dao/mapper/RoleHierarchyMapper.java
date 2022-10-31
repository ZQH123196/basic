package com.example.rbac0withhierarchy.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rbac0withhierarchy.dao.entity.Permission;
import com.example.rbac0withhierarchy.dao.entity.RoleHierarchy;
import org.apache.ibatis.annotations.Mapper;

@Mapper

public interface RoleHierarchyMapper extends BaseMapper<RoleHierarchy> {
}

package com.example.rbac0withgroup.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rbac0withgroup.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}

package com.example.rbac1.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.rbac1.dao.entity.UserGroupAdmin;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户组表 Mapper 接口
 * </p>
 *
 * @author zqh
 * @since 2022-10-31
 */
@Mapper
public interface UserGroupAdminMapper extends BaseMapper<UserGroupAdmin> {

}

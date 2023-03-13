package com.example.mpsimple.dao.service.impl;

import com.example.mpsimple.dao.mapper.UsersMapper;
import com.example.mpsimple.dao.entity.Users;
import com.example.mpsimple.dao.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zqh
 * @since 2023-03-09
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}

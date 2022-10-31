package com.example.rbac0withgroup.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rbac0withgroup.dao.entity.User;
import com.example.rbac0withgroup.dao.mapper.UserMapper;
import com.example.rbac0withgroup.dao.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}

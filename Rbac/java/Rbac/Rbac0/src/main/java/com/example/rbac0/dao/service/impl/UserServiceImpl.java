package com.example.rbac0.dao.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.rbac0.dao.entity.User;
import com.example.rbac0.dao.mapper.UserMapper;
import com.example.rbac0.dao.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}

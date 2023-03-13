package com.example.dao.service;

import com.example.dao.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsersServiceTest {
    @Resource
    IUsersService usersService;

    @Test
    public void test1() {
        List<Users> list = usersService.list();
        System.out.println(list);
    }
}
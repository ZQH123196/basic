package org.example.argument.controller;


import org.example.argument.annotation.LoginUser;
import org.example.argument.controller.vo.LoginUserVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Login")
public class LoginController {


    @RequestMapping("getLoginUserInfo")
    public LoginUserVo getLoginUserInfo(@LoginUser LoginUserVo loginUserVo) {
        return loginUserVo;
    }


}

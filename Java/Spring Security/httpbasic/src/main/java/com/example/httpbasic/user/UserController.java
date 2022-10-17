package com.example.httpbasic.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("User/")
public class UserController {

    @ResponseBody
    @RequestMapping("echo")
    public String echoServer(@PathVariable String str) {
        return str;
    }


}

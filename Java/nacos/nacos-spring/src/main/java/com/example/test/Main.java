package com.example.test;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        UserService bean = context.getBean(UserService.class);
        System.out.println(bean.test());

    }


}

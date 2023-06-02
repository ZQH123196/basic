package com.example.nestedservletreactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.reactive.context.StandardReactiveWebEnvironment;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Collections;

@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication appServlet = new SpringApplication(MyApplication.class);
//        appServlet.setWebApplicationType(WebApplicationType.SERVLET);
        appServlet.run(args);

//        SpringApplication appReactive = new SpringApplication(MyApplication.class);
//        appReactive.setWebApplicationType(WebApplicationType.REACTIVE);
//        appReactive.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
//        appReactive.run(args);

        // 启动基于 Servlet 的应用程序
        ServletWebServerApplicationContext servletApp = new ServletWebServerApplicationContext();
        servletApp.setConfigLocation("com.example.ServletApplication");
        servletApp.setDisplayName("Servlet Application");
        servletApp.setNamespace("servlet");
        servletApp.setServletContext(new MockServletContext());
        servletApp.refresh();

        // 启动基于 WebFlux 的应用程序
        AnnotationConfigApplicationContext webFluxApp = new AnnotationConfigApplicationContext();
//        webFluxApp.register(WebFluxApplication.class);
        webFluxApp.setDisplayName("WebFlux Application");
        webFluxApp.setNamespace("webflux");
        webFluxApp.refresh();
    }
}

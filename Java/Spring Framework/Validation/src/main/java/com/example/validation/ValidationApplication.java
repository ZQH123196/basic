package com.example.validation;

import com.example.validation.vo.Person;
import com.example.validation.vo.validator.PersonValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.Validator;

/**
 * 去 validator 项目，查看原生使用。
 * 这里是结合了 spring
 * https://zhuanlan.zhihu.com/p/196755956
 * https://www.baeldung.com/spring-boot-bean-validation
 * https://docs.spring.io/spring-framework/docs/5.3.10/reference/html/core.html#validation-beanvalidation
 */
@SpringBootApplication
@Slf4j
public class ValidationApplication implements ApplicationRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder()
                .sources(ValidationApplication.class)
                // 关闭 web 上下文以加速
                .web(WebApplicationType.NONE)
                .run(args);
    }


    // You can inject a reference to javax.validation.Validator if you prefer to work with the Bean Validation API directly
    @Autowired
    Validator validator;

    // LocalValidatorFactoryBean 同时继承了 javax 和 spring 的 validator
    // 所以在使用时，自动注入的不管是 javax 的还是 spring 的 validator 接口都可以支持,这两个接口的最终实现类都是 LocalValidatorFactoryBean
    LocalValidatorFactoryBean localValidatorFactoryBean;
    
    @Override
    public void run(ApplicationArguments args) throws Exception {

//        handleJavaxValidation();

        System.out.println("-----------------------------------");
        System.out.println(validator.getClass().getSimpleName());
    }


    //
    // 手动使用 spring 的 validator
    public void handleJavaxValidation() {
        Person person = Person.builder()
                .name("")
                .age(8999)
                .build();
        // 创建 Person 对应的 DataBinder
        DataBinder binder = new DataBinder(person);
        // 设置校验
        binder.setValidator(new PersonValidator());
        binder.validate();
        BindingResult results = binder.getBindingResult();
        log.info("results.hasErrors() = [{}]", results.hasErrors());
        // 因为 name 为 empty 同时 age 超过上限，所以应有两个错误
        log.info("results.getErrorCount() = [{}]", results.getErrorCount());
        // 将所有错误的信息打印出来
        results.getAllErrors().stream().forEach(objectError -> log.info(objectError.toString()));
    }

}

package com.example.validation.customvalidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * 核心是 @Constraint 注解，这个注解会在运行时扫描
 * 1. 这个注解在什么时候被获取？
 * @author ZQH12
 */
@Documented
@Constraint(validatedBy = {UserStatusValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UserStatus {
    String message() default "status 必须是 1000/1001/1002";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}

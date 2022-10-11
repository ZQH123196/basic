package com.eor.jsonschema.annontation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)

@Documented

@Target({ElementType.PARAMETER, ElementType.METHOD})

public @interface Schemavalidator {
    String schemaName();
    String message() default "Json数据校验失败";
}


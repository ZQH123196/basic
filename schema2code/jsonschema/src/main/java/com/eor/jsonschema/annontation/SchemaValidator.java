package com.eor.jsonschema.annontation;


import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Documented
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface SchemaValidator {
    String schemaName();
    String message() default "Schema 格式校验失败";
}


package com.example.reqres.advice;

import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * it is passed into a controller method as an @RequestBody or an HttpEntity method argument.
 * 因为 RequestBodyAdviceAdapter 名称带 body，所以只支持有 @RequestBody 注解的地方
 */
@Order(2)
@RestControllerAdvice
public class MyReqBodyAdvice extends RequestBodyAdviceAdapter {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {

//        String newbodyStr = "{\n" +
//                "    \"msg\": \"入参被 reqAdvice 修改\"\n" +
//                "}";

//        return newbodyStr;
        return body;
    }





}

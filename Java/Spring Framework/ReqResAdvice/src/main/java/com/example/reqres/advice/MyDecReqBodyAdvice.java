package com.example.reqres.advice;

import lombok.extern.slf4j.Slf4j;
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
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 先解密，为了简便这里使用 base64 就完事了
 * beforeBodyRead 跟 afterBodyRead 的区别就在于前者可以修改头部而已......
 */
@Order(1)
@RestControllerAdvice
@Slf4j
public class MyDecReqBodyAdvice extends RequestBodyAdviceAdapter {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }


    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        for (Field field : body.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(body);
                if (value instanceof String) {
                    byte[] decValue = Base64.getDecoder().decode(((String) value).getBytes("UTF-8"));
                    String decValueStr = new String(decValue, "UTF-8");
                    field.set(body, decValueStr);
                } else {
                    throw new Exception("目前只支持 String 类型的解密，value 必须为 String");
                }
            } catch (IllegalAccessException e) {
                log.error("反射获取值失败", e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return body;
    }
}

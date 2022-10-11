package com.example.reqres.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@RestControllerAdvice
public class MyReqAdvice extends RequestBodyAdviceAdapter {

    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }


    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {

        byte[] bodyBytes = new byte[inputMessage.getBody().available()];
        inputMessage.getBody().read(bodyBytes);

        String bodyStr = new String(bodyBytes, StandardCharsets.UTF_8);
        System.out.println("bodyStr = "+bodyStr);
        String newbodyStr = "{\n" +
                "    \"chang\": \"被 reqAdvice 修改\"\n" +
                "}";

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(newbodyStr.getBytes(StandardCharsets.UTF_8));

        HttpInputMessage httpInputMessage = new HttpInputMessage() {
            @Override
            public InputStream getBody() throws IOException {
                return byteArrayInputStream;
            }

            @Override
            public HttpHeaders getHeaders() {
                return inputMessage.getHeaders();
            }
        };

        return httpInputMessage;
    }
}

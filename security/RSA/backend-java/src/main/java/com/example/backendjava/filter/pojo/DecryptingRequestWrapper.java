package com.example.backendjava.filter.pojo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;

import javax.annotation.Resource;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DecryptingRequestWrapper extends HttpServletRequestWrapper {

    @Resource
    ObjectMapper objectMapper;

    private final String decryptedBody;

    public DecryptingRequestWrapper(HttpServletRequest request, String secretKey) throws IOException {
        super(request);
        String encryptedBody = IOUtils.toString(request.getInputStream(), StandardCharsets.UTF_8);
        this.decryptedBody = decrypt(encryptedBody, secretKey);
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new StringReader(decryptedBody));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(decryptedBody.getBytes());
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return byteArrayInputStream.available() == 0;
            }

            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setReadListener(ReadListener listener) {
                throw new UnsupportedOperationException();
            }

            @Override
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }

            @Override
            public int read(byte[] b) throws IOException {
                return byteArrayInputStream.read(b);
            }

            @Override
            public int read(byte[] b, int off, int len) throws IOException {
                return byteArrayInputStream.read(b, off, len);
            }

        };
    }

    private String decrypt(String encryptedBody, String secretKey) throws JsonProcessingException {
        // 使用自己的解密算法解密请求 body
        List<String> encList = objectMapper.readValue(encryptedBody, List.class);

        // collect 跟 forEachOrdered 一样可以按照原始顺序来收集结果
        return encList.stream().unordered().map(item -> rsaDec(item, secretKey)).collect(Collectors.joining());
    }

    private String rsaDec(String encStr, String secretKey) {
        return encStr;
    }
}

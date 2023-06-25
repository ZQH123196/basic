package com.example.java.sm.utils;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SM4;
import org.bouncycastle.util.encoders.Hex;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileEncUtil {

    private static byte[] sm4Key = SmUtil.sm4().getSecretKey().getEncoded();
    private static SM4 sm4 = SmUtil.sm4(sm4Key);
    private static Charset defaultCharset = Charset.forName("utf-8");


    public static void main(String[] args) throws IOException {

        String filePathStr = "E:\\Code\\Porject\\basic\\security\\SM\\like_https\\java\\test.txt";
        Path targetFilePath = Paths.get(filePathStr);

        byte[] encFileBytes = encFileSm4(targetFilePath);
        byte[] decFileBytes = decFileSm4(encFileBytes);

        System.out.println(new String(decFileBytes, defaultCharset));
    }


    /**
     * 加密的方式是
     * 1. 将二进制转为 16 进制表达
     * 2. 对 16 进制表达进行 sm4 加密
     *
     * @param targetFilePath
     * @return
     */
    public static byte[] encFileSm4(Path targetFilePath) throws IOException {
        // 文件不能大于 2g
        byte[] allBytes = Files.readAllBytes(targetFilePath);
        String hexString = Hex.toHexString(allBytes);
        return sm4.encrypt(hexString);
    }

    public static byte[] decFileSm4(byte[] encFileSm4) {
        String hexString = sm4.decryptStr(encFileSm4);
        return Hex.decode(hexString);
    }
}

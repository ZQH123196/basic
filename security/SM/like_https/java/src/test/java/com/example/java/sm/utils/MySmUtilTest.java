package com.example.java.sm.utils;

import cn.hutool.core.util.HexUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MySmUtilTest {
    /**
     * 生成sm2公钥和私钥测试(生成密钥对后将密钥存到SMUtil对应的常量中即可)
     */
    @Test
    public void createSm2KeyTest() {
        MySmUtil.createSm2Key();
    }

    /**
     * sm2非对称加密和解密测试
     */
    @Test
    public void sm2EncryptAndDecryptTest() {
        long start = System.currentTimeMillis();
        String text = "我是一段测试aaaa";
        String sm2Encrypt = MySmUtil.sm2Encrypt(text);
        System.out.println("加密结果：" + sm2Encrypt);
        String sm2Decrypt = MySmUtil.sm2Decrypt(sm2Encrypt);
        System.out.println("解密结果：" + sm2Decrypt);
        System.out.println(text.equals(sm2Decrypt));
        long end = System.currentTimeMillis();
        System.out.println("加密解密一次所花时间：" + (end - start) + "ms");
    }


    /**
     * sm3加密测试（无法解密，一般用于密码加密，验证就是将用户输入的密码也进行一次sm3加密再比较是否相同）
     */
    @Test
    public void sm3EncryptAndDecryptTest() {
        String encryptPassword = MySmUtil.sm3("123456");
        System.out.println(encryptPassword);
    }

    /**
     * 生成sm4密钥测试,这里得到的密钥是一个byte数组
     * @throws Exception
     */
    @Test
    public void createSm4KeyTest() throws Exception {
        byte[] bytes = MySmUtil.generateKey();
        for (int i = 0; i < bytes.length; i++) {
            System.out.println(bytes[i]);
        }
    }

    /**
     * 将sm4的key转成16进制字符串输出，前端使用的是16进制的key进行加解密
     */
    @Test
    public void sm4KeyToByteTest() {
        System.out.println(HexUtil.encodeHexStr(MySmUtil.SM4_KEY));
    }

    /**
     * sm4加密和解密测试
     */
    @Test
    public void sm4EncryptAndDecryptTest() {
//        String content = "123456789你好";
//        String encryptContent = MySmUtil.sm4Encrypt(content);
//        String decryptContent = MySmUtil.sm4Decrypt(encryptContent);
//        System.out.println("加密后内容：" + encryptContent);
//        System.out.println("解密后内容:" + decryptContent);
//        System.out.println(content.equals(decryptContent));
    }

}
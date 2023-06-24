package com.example.java.sm.utils;


import cn.hutool.core.util.HexUtil;
import cn.hutool.crypto.BCUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import org.bouncycastle.crypto.digests.SM3Digest;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.jcajce.provider.asymmetric.ec.BCECPublicKey;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.encoders.Hex;
import org.slf4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.security.Security;
import java.util.HashMap;
import java.util.Map;

/**
 * 来自
 * https://blog.csdn.net/ChOLg/article/details/119933634
 */
public class MySmUtil {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(MySmUtil.class);

    /**
     * key：redis 或其他存储区域的 key，由前端传入，一般前端可用 用户名+登录时间 来传入
     * value：sm4 的密钥，注意存入的是 hex2bytes 之后的值，而不是 hex
     */
    public static Map<String, byte[]> sm4KeyMap = new HashMap<>();

    /**
     * sm4的密钥需要16字节的字符串
     **/
    public static final String SM4_KEY = "1008610086100861";
    private static final String ALGORITHM_NAME = "SM4";
    private static final String ALGORITHM_ECB_PKCS5PADDING = "SM4/ECB/PKCS5Padding";
    /**
     * sm2的公钥和私钥生成之后存起来使用即可，一般会把公钥提供给前端
     **/
    public static final String SM2_PRIVATE_KEY = "00e6f9cd3dbe9d827c5b0949dc5ab9247543b7ff6c249207b5eb0765d78be84d2d";
    public static final String SM2_PUBLIC_KEY = "0430756f05b5e8dea62e044e7b6e61d0de8d16acc97bbc23a12506c3cb28536597f3cc59de599dcc117cf8299955287f79cd09ac60291e070a69e4b271c546817f";
    /**
     * sm2加密模式有两种：C1C2C3和C1C3C2（开始国密标准使用C1C2C3，新标准使用C1C3C2）
     * 0——C1C2C3、1——C1C3C2，前后端的 cipherMode 需要一致，此处都为 1
     */
    public static final SM2Engine.Mode SM2ENGINE_MODE = SM2Engine.Mode.C1C3C2;
    private static final String SM2BC_STR = "04";
    private static SM2 sm2 = null;

    /**
     * SM4算法目前只支持128位（即密钥16字节）
     */
    private static final int DEFAULT_KEY_SIZE = 128;
    private static final String DEFAULT_ENCODING = "utf-8";

    static {
        if (null == Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     * sm3加密，文摘算法，杂凑算法
     *
     * @param content 加密内容
     * @return
     */
    public static String sm3(String content) {
        String res = "";
        try {
            byte[] srcData = content.getBytes(DEFAULT_ENCODING);
            byte[] resultHash = hash(srcData);
            res = ByteUtils.toHexString(resultHash);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
     * sm3加密
     *
     * @param srcData 加密内容的byte数组
     **/
    private static byte[] hash(byte[] srcData) {
        SM3Digest digest = new SM3Digest();
        digest.update(srcData, 0, srcData.length);
        byte[] hash = new byte[digest.getDigestSize()];
        digest.doFinal(hash, 0);
        return hash;
    }


    /**
     * 生成sm4密钥,生成一次后将byte数组存起来使用即可（推荐：也可以自定义16字节字符串，本工具类使用的是自定义16字节字符串做密钥）
     * <p>建议使用org.bouncycastle.util.encoders.Hex将二进制转成HEX字符串</p>
     *
     * @return 密钥16位
     * @throws Exception 生成密钥异常
     */
    public static byte[] generateKey() throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(DEFAULT_KEY_SIZE, new SecureRandom());
        return kg.generateKey().getEncoded();
    }

    /***
     * SM4对称加密，
     * @param content 加密内容
     * @param key 密钥 需要128bit,即16个字符组成
     * @return
     */
    public static String sm4Encrypt(String content, String key) {
        String res = "";
        try {
            byte[] resByte = encryptEcbPkcs5Padding(content.getBytes(DEFAULT_ENCODING), key.getBytes(DEFAULT_ENCODING));
            res = Hex.toHexString(resByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /***
     * SM4对称加密，
     * @param content 加密内容
     * @return
     */
    public static String sm4Encrypt(String content) {
        String res = "";
        try {
            byte[] resByte = encryptEcbPkcs5Padding(content.getBytes(DEFAULT_ENCODING), SM4_KEY.getBytes(DEFAULT_ENCODING));
            res = Hex.toHexString(resByte);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    /***
     * SM4对称解密，
     * @param data 解密前数据
     * @param key 密钥 需要128bit,即16个字符组成
     * @return
     */
    public static String sm4Decrypt(String data, byte[] key) {
        return SmUtil.sm4(key).decryptStr(data);
    }

    /***
     * SM4对称解密，
     * @param data 解密前数据
     * @return
     */
//    public static String sm4Decrypt(String data) {
//        String res = "";
//        try {
//            byte[] resByte = decryptEcbPkcs5Padding(Hex.decode(data), SM4_KEY.getBytes(DEFAULT_ENCODING));
//            res = new String(resByte, DEFAULT_ENCODING);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return res;
//    }

    /**
     * 加密，SM4-ECB-PKCS5Padding
     *
     * @param data 要加密的明文
     * @param key  密钥16字节，使用Sm4Util.generateKey()生成
     * @return 加密后的密文
     * @throws Exception 加密异常
     */
    private static byte[] encryptEcbPkcs5Padding(byte[] data, byte[] key) throws Exception {
        return sm4work(data, key, ALGORITHM_ECB_PKCS5PADDING, null, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密，SM4-ECB-PKCS5Padding
     *
     * @param data 要解密的密文
     * @param key  密钥16字节，使用Sm4Util.generateKey()生成
     * @return 解密后的明文
     * @throws Exception 解密异常
     */
    private static byte[] decryptEcbPkcs5Padding(byte[] data, byte[] key) throws Exception {
        return sm4work(data, key, ALGORITHM_ECB_PKCS5PADDING, null, Cipher.DECRYPT_MODE);
    }

    /**
     * SM4对称加解密
     *
     * @param input   明文（加密模式）或密文（解密模式）
     * @param key     密钥
     * @param sm4mode sm4加密模式
     * @param iv      初始向量(ECB模式下传NULL)
     * @param mode    Cipher.ENCRYPT_MODE - 加密；Cipher.DECRYPT_MODE - 解密
     * @return 密文（加密模式）或明文（解密模式）
     * @throws Exception 加解密异常
     */
    private static byte[] sm4work(byte[] input, byte[] key, String sm4mode, byte[] iv, int mode)
            throws Exception {
        IvParameterSpec ivParameterSpec = null;
        if (null != iv) {
            ivParameterSpec = new IvParameterSpec(iv);
        }
        SecretKeySpec sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        Cipher cipher = Cipher.getInstance(sm4mode, BouncyCastleProvider.PROVIDER_NAME);
        if (null == ivParameterSpec) {
            cipher.init(mode, sm4Key);
        } else {
            cipher.init(mode, sm4Key, ivParameterSpec);
        }
        return cipher.doFinal(input);
    }

    /**
     * sm2解密
     *
     * @param data 密文
     * @return
     */
    public static String sm2Decrypt(String data) {
        String decryptData = "";
        try {
            long start = System.currentTimeMillis();
            initSm2Instance();
            //使用BC库解密需要在密文前面拼上”04“字符串(前端加密是不会加上04的，后端加密则会加上04，所以需要做多一层判断)
            //注意：请使用使用StringBuffer不要使用StringBuilder，前者是线程安全，后者非线程安全
            if (!data.startsWith(SM2BC_STR)) {
                data = new StringBuffer(SM2BC_STR).append(data).toString();
            }
            decryptData = sm2.decryptStr(data, KeyType.PrivateKey);
            long end = System.currentTimeMillis();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptData;
    }

    /**
     * sm2加密
     *
     * @param data 待加密文本
     * @return
     */
    public static String sm2Encrypt(String data) {
        String encryptData = "";
        try {
            long start = System.currentTimeMillis();
            log.info("初始化sm2实例");
            initSm2Instance();
            encryptData = sm2.encryptBcd(data, KeyType.PublicKey);
            long end = System.currentTimeMillis();
            log.info("sm2加密一次所花时间：{}ms", (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptData;
    }

    /**
     * 初始化sm2实例
     *
     * @return
     */
    private static void initSm2Instance() {
        // 不为空时直接打回
        if (sm2 != null) return;
        log.info("初始化sm2实例");
        sm2 = new SM2();
        ECPrivateKeyParameters privateKeyParameters = BCUtil.toSm2Params(SM2_PRIVATE_KEY);
        //产生的公钥为130字节，实际公钥为128字节，第一个字节表示是否压缩，这里用不上，所以从第二个字节开始截取横坐标和纵坐标
        String xhex = SM2_PUBLIC_KEY.substring(2, 66);
        String yhex = SM2_PUBLIC_KEY.substring(66, 130);
        ECPublicKeyParameters publicKeyParameters = BCUtil.toSm2Params(xhex, yhex);
        //设置公钥和私钥
        sm2.setPrivateKeyParams(privateKeyParameters);
        sm2.setPublicKeyParams(publicKeyParameters);
        //设置加密模式
        sm2.setMode(SM2ENGINE_MODE);
    }

    /**
     * 生成sm2公钥和私钥，一般生成之后将公钥和私钥存起来使用即可
     */
    public static void createSm2Key() {
        //创建sm2 对象
        SM2 createSm2Key = SmUtil.sm2();
        //这里会自动生成对应的随机秘钥对 , 注意！ 这里一定要强转，才能得到对应有效的秘钥信息
        byte[] privateKey = BCUtil.encodeECPrivateKey(createSm2Key.getPrivateKey());
        //这里公钥不压缩  公钥的第一个字节用于表示是否压缩  可以不要
        byte[] publicKey = ((BCECPublicKey) createSm2Key.getPublicKey()).getQ().getEncoded(false);
        //这里得到的 压缩后的公钥   ((BCECPublicKey) sm2.getPublicKey()).getQ().getEncoded(true);
        byte[] publicKeyEc = BCUtil.encodeECPublicKey(createSm2Key.getPublicKey());
        //打印当前的公私秘钥
        System.out.println("私钥: " + HexUtil.encodeHexStr(privateKey));
        System.out.println("公钥: " + HexUtil.encodeHexStr(publicKey));
        System.out.println("压缩后的公钥: " + HexUtil.encodeHexStr(publicKeyEc));
    }
}


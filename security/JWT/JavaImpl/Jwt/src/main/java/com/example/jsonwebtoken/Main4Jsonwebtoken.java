package com.example.jsonwebtoken;

import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.digest.HMac;
import cn.hutool.jwt.signers.HMacJWTSigner;
import io.jsonwebtoken.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.jsonwebtoken.impl.crypto.DefaultJwtSignatureValidator;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import static java.time.temporal.ChronoUnit.SECONDS;


public class Main4Jsonwebtoken {

    // We need a signing key, so we'll create one just for this example. Usually
    // the key would be read from your application configuration instead.
//    Key secretKey = MacSigner.generateKey();

    // 固定值的 key，值要长点，不然报错
    final byte[] bytes = Base64.encode("1234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890".getBytes()).getBytes();
    SecretKey secretKey = Keys.hmacShaKeyFor(bytes);

    private String buildKey() {
        SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS512);
        String base64Str = Base64.encode(secretKey.getEncoded());
        System.out.println(base64Str);
        return base64Str;
    }

    private static void printJwt(Jws<Claims> jwtDecodeObj) {
        JwsHeader jwt_HEADER = jwtDecodeObj.getHeader();
        Map<String, Object> jwt_PAYLOAD = jwtDecodeObj.getBody();
        String jwt_SIGNATURE = jwtDecodeObj.getSignature();

        System.out.println(jwt_HEADER);
        System.out.println(jwt_PAYLOAD);
        System.out.println(jwt_SIGNATURE);
    }


    /**
     * 推荐使用 claim，这样可以获得自动时间过期校验的好处
     * 生成之后，将 Head 跟 payload base64，然后跟签名组装起来 base64(Head) + "." + base64(payload) + "." + signature
     * 签名也是签名 base64 之后的内容，HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), your-256-bit-secret)
     * 这个 your-256-bit-secret 是用来加盐的，仅服务器知道
     *
     * @throws InterruptedException
     */
    @Test
    public void testClaim() throws InterruptedException {
        Date expireTime = Date.from(LocalDateTime.now().plus(3, SECONDS).atZone(ZoneId.of("Asia/Shanghai")).toInstant());
        String compactJws = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                // 超时
                .setExpiration(expireTime)
                .setSubject("user")
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
//        Thread.sleep(5*1000);
        System.out.println(compactJws);
        System.out.println(compactJws.split("[.]")[0]);

        JwtParser jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build();

        // 以下的这些 parse 都会自动校验 claim，DefaultJwtParser 内部已经集成了超时等时间的校验
        // jwtParser.parsePlaintextJws(compactJws);
        // jwtParser.parse(compactJws);
        Jws<Claims> jwtDecodeObj = jwtParser.parseClaimsJws(compactJws);


        printJwt(jwtDecodeObj);

    }


    /**
     * payload 跟 calim 同时存在时会报：
     * Both 'payload' and 'claims' cannot both be specified. Choose either one.
     * 这导致构造时的 setExpiration 方法不能使用，按照我的理解应该是可以共存才对，不明白。
     */
    @Test
    public void testPayload() throws InterruptedException {
        String compactJws = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setPayload("{\"args\":\"123\"}")
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();

        System.out.println(compactJws);
        System.out.println(compactJws.split("[.]")[0]);

        // parse 的时候就会校验 claim
        JwtParser jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build();
        Jws<Claims> jwtDecodeObj = jwtParser.parseClaimsJws(compactJws);


        printJwt(jwtDecodeObj);

    }


    /**
     * 注意 claim 其实就是一个 map
     * setExpiration、setSubject 其实都只是在设置这个 map 的值，因此要使用 addClaims 而不是 setClaims，setClaims 将会覆盖掉其他的值。
     *
     * @throws InterruptedException
     */
    @Test
    public void testPayloadWithClaim() throws InterruptedException {
        Date expireTime = Date.from(LocalDateTime.now().plus(3, SECONDS).atZone(ZoneId.of("Asia/Shanghai")).toInstant());
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", "eor");
        String compactJws = Jwts.builder()
                .setExpiration(expireTime)
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();


        // parse 的时候就会校验 claim
        JwtParser jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build();
        Jws<Claims> jwtDecodeObj = jwtParser.parseClaimsJws(compactJws);
        printJwt(jwtDecodeObj);
    }


    @Test
    public void testPayloadJustString() throws InterruptedException {
        String selfPayload = "abcd";
        String compactJws = Jwts.builder()
                .setPayload(selfPayload)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();


        // parse 的时候就会校验 claim
        JwtParser jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build();
        String payloadBase64 = compactJws.split("[.]")[1];
        String parsePayload = Base64.decodeStr(payloadBase64);
        Assertions.assertEquals(selfPayload, parsePayload);
    }


    @Test
    public void testClaimsJustMap2PayloadJson() throws InterruptedException {
        Date expireTime = Date.from(LocalDateTime.now().plus(3, SECONDS).atZone(ZoneId.of("Asia/Shanghai")).toInstant());
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", "eor");
        String compactJws = Jwts.builder()
                .setExpiration(expireTime)
                .addClaims(claims)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();


        // parse 的时候就会校验 claim
        JwtParser jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build();
        String payloadBase64 = compactJws.split("[.]")[1];
        String parsePayload = Base64.decodeStr(payloadBase64);
        // 打印结果 {"exp":1678653691,"name":"eor"}
        System.out.println(parsePayload);
    }

    @Test()
    public void testClaimsExpiration() throws InterruptedException {
        Date expireTime = Date.from(LocalDateTime.now().plus(3, SECONDS).atZone(ZoneId.of("Asia/Shanghai")).toInstant());
        String compactJws = Jwts.builder()
                .setExpiration(expireTime)
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();

        TimeUnit.SECONDS.sleep(3);

        JwtParser jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build();
        // parse 的时候就会校验 claim，将会超时
        Jws<Claims> claimsJws;
        try {
            claimsJws = jwtParser.parseClaimsJws(compactJws);
        } catch (ExpiredJwtException e) {
            System.out.println("成功捕获到超时异常！" + e.getMessage());
        }
    }


    @Test
    public void testJwtPart() {
        String compactJws = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.cThIIoDvwdueQB468K5xDc5633seEFoqwxjF_xSJyQQ";
        String[] split = compactJws.split("[.]");
        String header = Base64.decodeStr(split[0]);
        String payload = Base64.decodeStr(split[1]);
        String verifySign = Base64.decodeStr(split[2]);
//        new HMac().verify()
        // jsonwebtoken jwt 支持的算法从 SignatureAlgorithm 的常量中找
//        new HMacJWTSigner("ES256").verify()



        /**
         * 打印
         * {"alg":"HS256","typ":"JWT"}
         * {"sub":"1234567890","name":"John Doe","iat":1516239022}
         * SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
         */
        System.out.println(header);
        System.out.println(payload);
        System.out.println(verifySign);
    }
}
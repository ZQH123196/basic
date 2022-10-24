package com.example.jsonwebtoken;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Assert;
import io.jsonwebtoken.*;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import cn.hutool.core.util.HexUtil;
import io.jsonwebtoken.impl.crypto.MacSigner;
import io.jsonwebtoken.security.Keys;
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


    /**
     *  推荐使用 claim，这样可以获得自动时间过期校验的好处
     *  生成之后，将 Head 跟 payload base64，然后跟签名组装起来 base64(Head) + "." + base64(payload) + "." + signature
     *  签名也是签名 base64 之后的内容，HMACSHA256(base64UrlEncode(header) + "." + base64UrlEncode(payload), your-256-bit-secret)
     *  这个 your-256-bit-secret 是用来加盐的，仅服务器知道
     *
     * @throws InterruptedException
     */
    @Test
    public void claim() throws InterruptedException {

        Date date = Date.from(LocalDateTime.now().plus(5, SECONDS).atZone(ZoneId.of("Asia/Shanghai")).toInstant());
        String compactJws = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                // 超时
                .setExpiration(date)
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


        JwsHeader jwt_HEADER = jwtDecodeObj.getHeader();
        Map<String, Object> jwt_PAYLOAD = jwtDecodeObj.getBody();
        String jwt_SIGNATURE = jwtDecodeObj.getSignature();

        System.out.println(jwt_HEADER);
        System.out.println(jwt_PAYLOAD);
        System.out.println(jwt_SIGNATURE);

    }


    /**
     * payload 跟 calim 同时存在时会报：
     * Both 'payload' and 'claims' cannot both be specified. Choose either one.
     * 按照我的理解应该是可以共存才对，不明白。
     */
    @Test
    public void payload() {
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


        JwsHeader jwt_HEADER = jwtDecodeObj.getHeader();
        Map<String, Object> jwt_PAYLOAD = jwtDecodeObj.getBody();
        String jwt_SIGNATURE = jwtDecodeObj.getSignature();

        System.out.println(jwt_HEADER);
        System.out.println(jwt_PAYLOAD);
        System.out.println(jwt_SIGNATURE);

    }



}

package com.example.backendjavaspringboot.jwt.pojo;

import cn.hutool.core.util.IdUtil;
import cn.hutool.json.JSONUtil;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 构建 jwt payload
 **/
public class JwtPayloadBuilder {

    private Map<String, String> payload = new HashMap<>();
    /**
     * 附加的属性
     */
    private Map<String, String> additional;
    /**
     * jwt签发者
     **/
    private String iss;
    /**
     * jwt所面向的用户
     **/
    private String sub;
    /**
     * 接收jwt的一方
     **/
    private String aud;
    /**
     * jwt的过期时间，这个过期时间必须要大于签发时间
     **/
    private LocalDateTime exp;
    /**
     * jwt的签发时间
     **/
    private LocalDateTime iat = LocalDateTime.now();
    /**
     * 权限集
     */
    private Set<String> roles = new HashSet<>();
    /**
     * jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
     **/
    private String jti = IdUtil.simpleUUID();

    public JwtPayloadBuilder iss(String iss) {
        this.iss = iss;
        return this;
    }


    public JwtPayloadBuilder sub(String sub) {
        this.sub = sub;
        return this;
    }

    public JwtPayloadBuilder aud(String aud) {
        this.aud = aud;
        return this;
    }


    public JwtPayloadBuilder roles(Set<String> roles) {
        this.roles = roles;
        return this;
    }

    /**
     * 简易时间模式匹配：
     * 1s、1m、1h、1d
     * @param timePattern
     * @return
     */
    public JwtPayloadBuilder exp(String timePattern) {
        if (this.exp != null) throw new RuntimeException("已经存在超时时间！");
        String pattern = timePattern.toLowerCase();
        Integer time = Integer.valueOf(pattern.substring(0, pattern.length() - 1));
        String timeFlag = pattern.substring(pattern.length() - 1);
        Assert.isTrue(time > 0, "jwt expireDate must after now");
        switch (timeFlag) {
            case "s":
                this.exp = this.iat.plusSeconds(time);
                break;
            case "m":
                this.exp = this.iat.plusMinutes(time);
                break;
            case "h":
                this.exp = this.iat.plusHours(time);
                break;
            case "d":
                this.exp = this.iat.plusDays(time);
                break;
        }
        return this;
    }

    public JwtPayloadBuilder additional(Map<String, String> additional) {
        this.additional = additional;
        return this;
    }

    public String builder() {
        payload.put("iss", this.iss);
        payload.put("sub", this.sub);
        payload.put("aud", this.aud);
        payload.put("exp", this.exp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        payload.put("iat", this.iat.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        payload.put("jti", this.jti);

        if (!CollectionUtils.isEmpty(additional)) {
            payload.putAll(additional);
        }
        payload.put("roles", JSONUtil.toJsonStr(this.roles));
        return JSONUtil.toJsonStr(JSONUtil.parse(payload));

    }

}

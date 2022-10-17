package com.example.authenticationformlogin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

/**
 * 这是一个经典的 权限实现，用户以及其所持有的权限
 * 参考 org.springframework.security.core.userdetails.User 来实现自定义
 * 返回一个 UserDetails 类型的实体，security 会自动根据密码和用户相关状态（是否锁定、是否启停、是否过期等）判断用户登录成功或者失败。
 * 实现了 CredentialsContainer 接口的 eraseCredentials()，用户认证成功后，会擦除密码。
 */
@Component
@AllArgsConstructor
@Builder
public class LoginUser implements UserDetails, CredentialsContainer {
    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private static final Log logger = LogFactory.getLog(User.class);

    private String password;

    private final String username;

    private final Set<GrantedAuthority> authorities;

    private final boolean accountNonExpired;

    private final boolean accountNonLocked;

    private final boolean credentialsNonExpired;

    private final boolean enabled;

    /**
     * 认证完成后，擦除密码
     */
    @Override
    public void eraseCredentials() {
        this.password = null;
    }

    /**
     * 获取该用户可用的权限列表
     * @return Returns the authorities granted to the user. Cannot return null.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * 账户是否未过期，返回 false 说明过期，security 不会让其通过
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    /**
     * 该用户是否无锁，返回 false 说明用户被锁定，无法进行身份验证
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    /**
     * 是否是失效的用户凭据(密码)，已失效的凭据无法认证，用于 token
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    /**
     * 用户是否被启用或禁用。被禁用的用户无法进行身份验证。
     */
    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}

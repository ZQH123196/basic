package springsecuritydemo.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * 基于数据库完成认证
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     *
     * @param username 前端传入的用户名
     * @return UserDetails 实现为 security 内部的 User，被 security 其他机制所使用
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}

package com.example.authenticationhttpbasic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.springframework.security.config.Customizer.withDefaults;


@EnableWebSecurity
public class MultiHttpSecurityConfig {

    static String username = "admin";
    static String password = "1008610086";
    static String[] roles = new String[]{"ADMIN", "USER"};
    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserDetails userDetails = User.withUsername(username)
                .password(encoder.encode(password))
                .roles(roles)
                .build();
        manager.createUser(userDetails);
        return manager;
    }

    /**
     * We can configure multiple HttpSecurity instances just as we can have multiple <http> blocks.
     * The key is to extend the WebSecurityConfigurerAdapter multiple times.
     */

    @Configuration
    @Order(1)
    public class HttpBasicWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        /**
         * WebSecurity 的作用范围是整个 Spring Security 的 FilterChainProxy。
         * WebSecurity 的层次高于 HttpSecurity，HttpSecurity 仅仅是配置 FilterChainProxy 中的一环（SecurityFilterChain）。
         * 由于 WebSecurity 目的是整个应用，所以还包括了 Firewall 防护墙过滤的功能
         * @param web
         * @throws Exception
         */
        @Override
        public void configure(WebSecurity web) throws Exception {
            // 忽略页面的静态资源，WebSecurity 作用域全局，相当于决定是否使用 security 的功能吧
            // 因此配置了这个之后就不会进入 configure(HttpSecurity http) 和 FilterChainProxy 中
            web.ignoring().antMatchers("/static/**");
        }

        /**
         * .authorizeRequests((requests) -> requests.anyRequest().authenticated());
         * 意思是对请求进行授权，范围是任何请求，都需要认证。这个全部请求的应总是在最后面。
         * .anyRequest().authenticated() to be used only once.
         * .anyRequest 只能被使用一次，多次使用将会提示错误，因此总是放在最后。
         *
         * HttpBasic模式要求传输的用户名密码使用 Base64 模式进行加密。
         * 如果用户名是 "admin" ，密码是 "admin"，则将字符串 "admin:admin" 使用 Base64 编码算法加密。
         * 加密结果可能是：YWtaW46YWRtaW4=。
         * @param http
         * @throws Exception
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // 对请求进行授权 对于 anyRequest() 都要进行认证。
            http.authorizeRequests((requests) -> {
                requests.antMatchers("/anyoneAllow/**").permitAll();
                requests.anyRequest().authenticated();
            });
            http.httpBasic(withDefaults());
        }
    }


    @Configuration
    public class FormLoginWebSecurityConfigurerAdapter  extends WebSecurityConfigurerAdapter {
        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring().antMatchers("/static/**");
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(authorize -> authorize
                            .anyRequest().authenticated()
                    )
                    .formLogin(withDefaults());
        }
    }
}

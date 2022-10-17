package com.example.httpbasic.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {


    /**
     * WebSecurity 的作用范围是整个 Spring Security 的 FilterChainProxy。
     * WebSecurity 的层次高于 HttpSecurity，HttpSecurity 仅仅是配置 FilterChainProxy 中的一环（SecurityFilterChain）。
     * 由于 WebSecurity 目的是整个应用，所以还包括了 Firewall 防护墙过滤的功能
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        // 忽略页面的静态资源，配置了这个之后就不会进入 configure(HttpSecurity http) 中
        // 这个相当于是 configure(HttpSecurity http) 之前运行的
        web.ignoring().antMatchers("/static/**");
    }

    /**
     * http.authorizeRequests((requests) -> requests.anyRequest().authenticated()).认证方式（httpBasic、formLogin）;
     * 注意：认证方式放在后面，因为先对 url 进行过滤，过滤之后在考虑认证。当然，放前方都兼容，但最好符合逻辑。
     *
     * .authorizeRequests((requests) -> requests.anyRequest().authenticated());
     * 意思是对请求进行授权，范围是任何请求，都需要认证。这个全部请求的应总是在最后面。
     * <p>
     * .authorizeRequests((requests) -> requests.antMatchers("static/**").permitAll());
     * 意思是对请求进行授权，范围是匹配表达式的请求，允许一切权限。这个一般是给登录页面静态资源的。
     * 有些人会认为只有登录页面的可以允许全部，而其他页面的不允许，但这样就要求前端是能独立分割出登录页面请求的，这很难。
     *
     * .anyRequest().authenticated() to be used only once .
     * anyRequest 只能被使用一次，多次使用将会提示错误。
     * @param http
     * @throws Exception
     */
    /**
     * HttpBasic模式要求传输的用户名密码使用Base64模式进行加密。如果用户名是 "admin" ，
     * 密码是“ admin”，则将字符串"admin:admin" 使用Base64编码算法加密。加密结果可能是：
     * YWtaW46YWRtaW4=。HttpBasic模式真的是非常简单又简陋的验证模式，Base64的加密算法是
     * 可逆的,想要破解并不难.
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http); // 父类实现里面有 http basic 验证，不太安全，所以关掉
        // httpbasic
//        http
//                .httpBasic()
//                .and()
//                .authorizeRequests((requests) -> requests.anyRequest().authenticated());
//
//        http
//                .formLogin()
//                .loginPage("/login")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .anyRequest().authenticated();

        http.authorizeRequests().antMatchers().hasRole("ADMIN");


        http
                .formLogin()
                .loginProcessingUrl("/login") // 前后端分离，访问该 url 将启用的操作，表单登录提交到这里
                .usernameParameter("username") // 自定义 UsernamePasswordAuthenticationFilter 的登录用户名
                .passwordParameter("password")
                .successForwardUrl("/") // 登录成功后跳转的页面
                .and()
                .authorizeRequests()
                .antMatchers("/login").permitAll()
                .anyRequest().authenticated();


        // 关闭 csrf 保护，也就是关闭跨站攻击保护
        // csrf 简单的说就是登录成功后，后端会生成一个唯一值给前端，前端将其保存
        http.csrf().disable();

        // <iframe> 的加载权限，这里允许加载同域的 <iframe>
        http.headers().frameOptions().sameOrigin();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }


}

package com.isxcode.ispring.config;

import com.isxcode.ispring.filter.JwtFilter;
import com.isxcode.ispring.security.authentication.UserAuthenticationManager;
import com.isxcode.ispring.security.authorization.UserAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * spring security 配置中心
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-27
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 注入身份管理器
     */
    @Override
    public AuthenticationManager authenticationManagerBean() {

        return new UserAuthenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 禁用csrf防护
        http.csrf().disable();

        // 拦截actuator请求
//        http.authorizeRequests().antMatchers("/actuator/**").hasRole("ADMIN");
//
        // 放行登录接口
        http.authorizeRequests().antMatchers("/userAuth").permitAll();

        // 添加token拦截器
        UserAuthFilter userAuthFilter = new UserAuthFilter();
        userAuthFilter.setAuthenticationManager(authenticationManagerBean());
        http.addFilterAfter(userAuthFilter, JwtFilter.class);

//        http.authenticationProvider()
        // 没有权限自动跳转登录页面
//        http.formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/isxcode/userAuth")
//                .permitAll();

        super.configure(http);
    }


    /**
     * 配置内存用户权限
     *
     * @since 2019-12-18
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 内存用户
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("user")).roles("USER");
        super.configure(auth);
    }

    /**
     * 自定义加密属性
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(16);
    }

    //    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//
//        return new UserDetailsServiceImpl();
//    }

}

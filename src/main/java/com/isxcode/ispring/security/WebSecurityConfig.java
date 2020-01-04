package com.isxcode.ispring.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * spring security 配置中心
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-27
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public UserDetailsService userDetailsServiceBean() {

        return new UserDetailsServiceImpl();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() {

        return new AuthenticationManagerImpl();
    }

    @Bean
    public AbstractAuthenticationProcessingFilter loginAuthenticationProcessingFilterBean() {

        LoginAuthenticationProcessingFilter loginAuthenticationProcessingFilter = new LoginAuthenticationProcessingFilter();
        loginAuthenticationProcessingFilter.setAuthenticationManager(authenticationManagerBean());
        return loginAuthenticationProcessingFilter;
    }

    @Bean
    public AbstractAuthenticationProcessingFilter tokenAuthenticationProcessingFilterBean() {

        TokenAuthenticationProcessingFilter tokenAuthenticationProcessingFilter = new TokenAuthenticationProcessingFilter();
        tokenAuthenticationProcessingFilter.setAuthenticationManager(authenticationManagerBean());
        return tokenAuthenticationProcessingFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 禁用csrf防护
        http.csrf().disable();

        // 设置放行路径
        http.authorizeRequests().antMatchers("/sfd").permitAll();

        // 关闭session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 禁用缓存
        http.headers().cacheControl();

        // actuator权限控制
        http.authorizeRequests().antMatchers("/actuator/**").hasRole("ADMIN");

        // 启动token拦截器
        http.addFilterBefore(tokenAuthenticationProcessingFilterBean(), UsernamePasswordAuthenticationFilter.class);

//        http.authenticationProvider(new TokenAuthenticationProviderImpl());

//        http.userDetailsService(userDetailsServiceBean());

        // 没有权限自动跳转登录页面
//        http.formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/isxcode/userAuth")
//                .permitAll();

//        http.exceptionHandling()
//                .accessDeniedHandler(new AccessDeniedHandlerImpl())
//                .authenticationEntryPoint(new AuthenticationEntryPointImpl());

        super.configure(http);
    }

    /**
     * authenticationManager 权限配置
     *
     * @since 2019-12-18
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(new LoginAuthenticationProviderImpl());
        auth.authenticationProvider(new TokenAuthenticationProviderImpl());
        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("user")).roles("USER");
        super.configure(auth);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}

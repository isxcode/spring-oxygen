//package com.isxcode.ispring.security;
//
//import com.isxcode.ispring.security.UserSecurityFilter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
///**
// * spring security 配置中心
// *
// * 集成本地身份验证系统
// * 集成spring security
// * 1- oauth 支持第三方登录 server
// * 2- oauth 调用第三方登录
// * 3- oauth 做成oauth client
// * 4- jwt校验接口
// * 5- access-token  标准化
// * 6- refresh-token  标准化
// *
// *
// * @author ispong
// * @version v0.1.0
// * @date 2019-11-27
// */
//@Configuration
//@EnableWebSecurity
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return new UserAuthenticationManager();
//    }
//
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//
//        // 禁用csrf防护
//        http.csrf().disable();
//        // 拦截actuator请求
//        http.authorizeRequests().antMatchers("/actuator/**").hasRole("ADMIN");
////
////        UserSecurityFilter userSecurityFilter = new UserSecurityFilter();
////        userSecurityFilter.setAuthenticationManager(authenticationManagerBean());
////        http.antMatcher("/getUser").addFilter(userSecurityFilter);
//
//        // 放行登录接口
////        http.authorizeRequests().antMatchers("/test", "/userAuth").permitAll();
//
//
////        http.userDetailsService().formLogin().loginPage()
//
//        // 没有权限自动跳转登录页面
//        http.formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/isxcode/userAuth")
//                .permitAll();
//
//        super.configure(http);
//    }
//
//
//    /**
//     * 用户登录入口
//     *
//     * @param
//     * @return
//     * @since 2019-12-18
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("user")).roles("USER");
//        super.configure(auth);
//    }
//
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        return new UserDetailsServiceImpl();
//    }
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(16);
//    }
//
//    @Bean
//    JwtDecoder jwtDecoder() {
//        return NimbusJwtDecoder.fromJwkSetUri(this.jwkSetUri)
//                .jwsAlgorithm(RS512).build();
//    }
//}

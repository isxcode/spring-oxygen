//package com.isxcode.oxygen.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.web.filter.OncePerRequestFilter;
//
///**
// * spring security 配置中心
// *
// * @author ispong
// * @version v0.1.0
// * @date 2019-11-27
// */
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//
////    @Override
////    public UserDetailsService userDetailsServiceBean() {
////
////        return new UserDetailsServiceImpl();
////    }
//
////    @Override
////    public AuthenticationManager authenticationManagerBean() {
////        return new AuthenticationManagerImpl();
////    }
//
////
////    @Bean
////    public AbstractAuthenticationProcessingFilter loginAuthenticationProcessingFilterBean() {
////
////        LoginAuthenticationProcessingFilter loginAuthenticationProcessingFilter = new LoginAuthenticationProcessingFilter();
////        loginAuthenticationProcessingFilter.setAuthenticationManager(authenticationManagerBean());
////        return loginAuthenticationProcessingFilter;
////    }
////
//
//    @Bean
//    public OncePerRequestFilter tokenAuthenticationFilterBean() {
//
//        return new TokenAuthenticationFilter();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        // 开启跨域
////        http.cors();
////
////        // 禁用csrf防护
//        http.csrf().disable();
////
////        // 设置放行路径,spring-security拦截器不会拦截
//        http.authorizeRequests().antMatchers("/weChat/weChatConfig").permitAll();
////      开启请求头拦截
////        http.httpBasic();
//
////        // 关闭session 打开会出现302
////        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////
////        http.formLogin();
////
////        // 禁用缓存
////        http.headers().cacheControl();
////
////        // actuator权限控制
//////        http.authorizeRequests().antMatchers("/actuator/**").hasRole("ADMIN");
////
////        // 启动token拦截器
//        http.addFilterBefore(tokenAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);
//
////        http.authenticationProvider(new TokenAuthenticationProviderImpl());
//
////        http.userDetailsService(userDetailsServiceBean());
//
//        // 没有权限自动跳转登录页面
////        http.formLogin()
////                .loginPage("/login")
////                .loginProcessingUrl("/isxcode/userAuth")
////                .permitAll();
//
////        http.exceptionHandling()
////                .accessDeniedHandler(new AccessDeniedHandlerImpl())
////                .authenticationEntryPoint(new AuthenticationEntryPointImpl());
//
////        super.configure(http);
//
////        WebAsyncManagerIntegrationFilter
//
////        BasicAuthenticationFilter
//
////                http
////                //关闭跨站请求防护
////                .cors().and().csrf().disable()
////                //允许不登陆就可以访问的方法，多个用逗号分隔
////                .authorizeRequests().antMatchers("/userAuth").permitAll()
////                //其他的需要授权后访问
////                .anyRequest().authenticated()
////                .and()
////                //增加登录拦截
////                .addFilterBefore(tokenAuthenticationProcessingFilterBean(), UsernamePasswordAuthenticationFilter.class)
////                //增加是否登陸过滤
//////                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
////                // 前后端分离是无状态的，所以暫時不用session，將登陆信息保存在token中。
////                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
//
////        super.configure(http);
//
//    }
//
//    /**
//     * authenticationManager 权限配置
//     *
//     * @since 2019-12-18
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
////        auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(passwordEncoder());
//        auth.inMemoryAuthentication().withUser("admin").password("{noop}admin").roles("ADMIN");
////        auth.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("user")).roles("USER");
//        // 下面代码不注释,会出现无法载入自定义用户
////        super.configure(auth);
//    }
//
////    @Bean
////    public BCryptPasswordEncoder passwordEncoder() {
////
////        return new BCryptPasswordEncoder();
////    }
//}

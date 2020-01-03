package com.isxcode.ispring.security;

//import com.isxcode.ispring.filter.JwtFilter;
import com.isxcode.ispring.config.CorsConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
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

    private final CorsConfig corsConfig;

    public WebSecurityConfig(CorsConfig corsConfig) {

        this.corsConfig = corsConfig;
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {

        return new UserDetailServiceImpl();
    }

    @Override
    public AuthenticationManager authenticationManagerBean() {

        return new AuthenticationManagerImpl();
    }

    @Bean
    public AbstractAuthenticationProcessingFilter LoginAuthenticationProcessingFilter() {

        LoginAuthenticationProcessingFilter loginAuthenticationProcessingFilter = new LoginAuthenticationProcessingFilter();
        loginAuthenticationProcessingFilter.setAuthenticationManager(authenticationManagerBean());
        return loginAuthenticationProcessingFilter;
    }

    @Bean
    public AbstractAuthenticationProcessingFilter TokenAuthenticationProcessingFilter() {

        TokenAuthenticationProcessingFilter tokenAuthenticationProcessingFilter = new TokenAuthenticationProcessingFilter();
        tokenAuthenticationProcessingFilter.setAuthenticationManager(authenticationManagerBean());
        return tokenAuthenticationProcessingFilter;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 不进入filter

        // 禁用csrf防护
        http.csrf().disable();

        // 设置放行路径
        http.authorizeRequests().antMatchers("/sfd").permitAll();

        // 关闭session
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        // 禁用缓存
        http.headers().cacheControl();

//        httpSecurity.exceptionHandling()
//                .accessDeniedHandler(restfulAccessDeniedHandler)
//                .authenticationEntryPoint(restAuthenticationEntryPoint);


        // 获取用户的信息
        http.userDetailsService(userDetailsServiceBean());

        // 拦截器
        http.addFilterBefore(LoginAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(TokenAuthenticationProcessingFilter(), UsernamePasswordAuthenticationFilter.class);

//        http.exceptionHandling().authenticationEntryPoint();

        //AuthenticationManager会调用ProviderManager来获取用户验证信息
//        http.

        // 拦截actuator请求
//        http.authorizeRequests().antMatchers("/actuator/**").hasRole("ADMIN");
//
        // 放行登录接口
//        http.authorizeRequests().antMatchers("/userAuth").permitAll();


//        // 添加token拦截器
//        UserAuthFilter userAuthFilter = new UserAuthFilter();
//        userAuthFilter.setAuthenticationManager(authenticationManagerBean());
//        http.addFilterAfter(userAuthFilter, JwtFilter.class);
//        http.authenticationProvider()

//        http.antMatcher("/**").addFilter(jwtFilter);
        // 没有权限自动跳转登录页面
//        http.formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/isxcode/userAuth")
//                .permitAll();

        http.exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandlerImpl())
                .authenticationEntryPoint(new AuthenticationEntryPointImpl());

        super.configure(http);
    }

    /**
     * authenticationManager 权限配置
     *
     * @since 2019-12-18
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

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

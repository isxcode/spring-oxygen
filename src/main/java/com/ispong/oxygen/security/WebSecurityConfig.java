/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ispong.oxygen.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 安全控制中心
 *
 * @author ispong
 * @since 0.0.1
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 自定义拦截器放行路径
     */
    private List<String> excludeUrlPaths = Arrays.asList(
            "/userAuth",
            "/file/**",
            "/login",
            "/logout",
            // 系统监控
            "/actuator/**",
            // swagger 放行路径
            "/",
            "/csrf",
            "/v2/api-docs",
            "/swagger-ui.html/**",
            "/swagger-resources/**",
            "/webjars/springfox-swagger-ui/**");

    /**
     * 任何人都可以访问
     */
    private List<String> allPaths = Arrays.asList(
            "/file/**",
            "/userAuth",
            "/login",
            "/logout");

    /**
     * 管理员控制权限
     */
    private List<String> adminPaths = Arrays.asList(
            "/actuator/**",
            // swagger 放行路径
            "/",
            "/csrf",
            "/v2/api-docs",
            "/swagger-ui.html/**",
            "/swagger-resources/**",
            "/webjars/springfox-swagger-ui/**");

    /**
     * 自定义 UserDetailsService
     *
     * @author ispong
     * @since 0.0.1
     */
    public UserDetailsService initUserDetailsServiceBean() {

        return new UserDetailsServiceImpl();
    }

    /**
     * 自定义 List[AuthenticationProvider]
     *
     * @return 权限处理器
     * @since 0.0.1
     */
    public List<AuthenticationProvider> authenticationProviderBean() {

        List<AuthenticationProvider> authenticationProviders = new ArrayList<>();
        authenticationProviders.add(new AuthenticationProviderImpl(initUserDetailsServiceBean()));
        return authenticationProviders;
    }

    /**
     * 自定义 AuthenticationManager
     *
     * @author ispong
     * @since 0.0.1
     */
    public AuthenticationManager initAuthenticationManagerBean() {

        return new AuthenticationManagerImpl(authenticationProviderBean());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.cors();
        http.csrf().disable();
        // 不能禁用session 会影响原有的spring-security使用
        // http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.headers().cacheControl();
        // 开启X-Frame-Options
        http.headers().frameOptions().disable();

        // 放行全部
//        http.authorizeRequests().antMatchers("/**").permitAll();

        // 系统登录控制
        http.authorizeRequests().antMatchers(adminPaths.toArray(new String[0])).hasRole("OXYGEN_ADMIN");
        http.authorizeRequests().antMatchers(allPaths.toArray(new String[0])).permitAll();

        // 添加拦截器
        http.antMatcher("/**").addFilterBefore(new JwtAuthenticationFilter(initAuthenticationManagerBean(), excludeUrlPaths), UsernamePasswordAuthenticationFilter.class);

        super.configure(http);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication().withUser("admin").password(passwordEncoder().encode("admin")).roles("OXYGEN_ADMIN");
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }
}

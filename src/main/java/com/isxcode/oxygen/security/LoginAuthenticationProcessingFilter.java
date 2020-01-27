//package com.isxcode.ispring.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 登录拦截器 代替UsernamePasswordAuthenticationFilter
// *
// * @author ispong
// * @version v0.1.0
// * @date 2020-01-04
// */
//@Slf4j
//public class LoginAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {
//
//    public LoginAuthenticationProcessingFilter() {
//
//        super(new AntPathRequestMatcher("/login", "GET"));
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//
//        logger.info("权限拦截器");
//
//
//        AuthenticationManager authenticationManager = getAuthenticationManager();
//
//        return null;
//    }
//
//}

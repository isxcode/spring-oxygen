//package com.isxcode.ispring.security;
//
//import org.springframework.security.authentication.AuthenticationServiceException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.RequestMatcher;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.Objects;
//
///**
// * spring security 拦截器配置
// *
// * @author ispong
// * @version v0.1.0
// * @date
// */
//public class UserSecurityFilter extends AbstractAuthenticationProcessingFilter {
//
//    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "username";
//    public static final String SPRING_SECURITY_FORM_PASSWORD_KEY = "password";
//    private String usernameParameter = "username";
//    private String passwordParameter = "password";
//    private boolean postOnly = true;
//
//    public UsernamePasswordAuthenticationFilter() {
//        //1.匹配URL和Method
//        super(new AntPathRequestMatcher("/login", "POST"));
//    }
//
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
//        if (this.postOnly && !request.getMethod().equals("POST")) {
//            //啥？你没有用POST方法，给你一个异常，自己反思去
//            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
//        } else {
//            //从请求中获取参数
//            String username = this.obtainUsername(request);
//            String password = this.obtainPassword(request);
//            //我不知道用户名密码是不是对的，所以构造一个未认证的Token先
//            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
//            //顺便把请求和Token存起来
//            this.setDetails(request, token);
//            //Token给谁处理呢？当然是给当前的AuthenticationManager喽
//            return this.getAuthenticationManager().authenticate(token);
//        }
//    }
//
//}

//package com.isxcode.ispring.security;
//
//import com.isxcode.ispring.exception.IsxcodeException;
//import com.isxcode.ispring.model.dto.UserDto;
//import com.isxcode.ispring.utils.JwtUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//import org.springframework.util.Assert;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * token拦截器 代替UsernamePasswordAuthenticationFilter
// *
// * @author ispong
// * @version v0.1.0
// * @date 2020-01-03
// */
//@Slf4j
//public class TokenAuthenticationFilter extends OncePerRequestFilter {
//
//    private AuthenticationManager authenticationManager = new AuthenticationManagerImpl();
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        // 拦截加载器
//        logger.info("拦截加载器");
//
//        // 获取请求头
//        String token = request.getHeader("token");
//        Assert.isNull(token, "token is null");
//
//        UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(token, "");
//        Authentication authenticate = authenticationManager.authenticate(userAuthenticationToken);
//        SecurityContextHolder.getContext().setAuthentication(authenticate);
//        doFilter(request, response, filterChain);
//    }
//}
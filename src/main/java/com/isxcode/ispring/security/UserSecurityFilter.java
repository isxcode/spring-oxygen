//package com.isxcode.ispring.security;
//
//import com.isxcode.ispring.model.dto.UserDto;
//import com.isxcode.ispring.utils.JwtUtils;
//import io.jsonwebtoken.JwtException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * 身份验证拦截器
// *
// * 选择原始的拦截器 AbstractAuthenticationProcessingFilter
// * username那个拦截器里面的方法没有必要实现
// *
// * @author ispong
// * @version v0.1.0
// * @date 2019-12-16
// */
//@Slf4j
//public class UserSecurityFilter extends AbstractAuthenticationProcessingFilter {
//
//    protected UserSecurityFilter(String defaultFilterProcessesUrl) {
//
//        super(defaultFilterProcessesUrl);
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
//
//        // 获取token
//        String token = request.getHeader("token");
//        if (token == null) {
//            logger.info("请求头没有token 做处理");
//        }
//        try {
//            // 解析token
//            UserDto userDto = JwtUtils.decryptJwt(token, UserDto.class);
//            // userDto 带上权限
//            // 组装Authentication
//            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDto, token);
//            // 权限管理
//            getAuthenticationManager().authenticate(authentication);
//            // 保存进SecurityContextHolder
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//            return authentication;
//        } catch (JwtException e) {
//            // 认证失败处理
//            logger.info("jwt解析失败 做处理");
//            return null;
//        }
//    }
//}

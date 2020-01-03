//package com.isxcode.ispring.filter;
//
//import com.isxcode.ispring.common.BaseFilter;
//import com.isxcode.ispring.exception.IsxcodeException;
//import com.isxcode.ispring.model.dto.UserDto;
//import com.isxcode.ispring.utils.JwtUtils;
//import io.jsonwebtoken.JwtException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class JwtFilter extends BaseFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//
//        // 获取token
//        String token = request.getHeader("token");
//        if (token == null) {
//            throw new IsxcodeException("token 异常");
//        }
//
//        // 解析token
//        UserDto userDto;
//        try {
//            userDto = JwtUtils.decryptJwt(token, UserDto.class);
//        } catch (JwtException e) {
//            throw new IsxcodeException("jwt解析失败 做处理");
//        }
//
//        // 生成用户authentication
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDto, token);
//
//        // 存进内存
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//        filterChain.doFilter(request, response);
//    }
//}

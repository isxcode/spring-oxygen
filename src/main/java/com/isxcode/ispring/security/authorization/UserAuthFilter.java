package com.isxcode.ispring.security.authorization;

import com.isxcode.ispring.exception.IsxcodeException;
import com.isxcode.ispring.model.dto.UserDto;
import com.isxcode.ispring.utils.JwtUtils;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 身份验证拦截器
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-12-16
 */
@Slf4j
@Order(1)
public class UserAuthFilter extends AbstractAuthenticationProcessingFilter {

    public UserAuthFilter() {

        super(new AntPathRequestMatcher("/**", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        // 获取token
        String token = request.getHeader("token");
        if (token == null) {
            throw new IsxcodeException("token 异常");
        }

        // 解析token
        UserDto userDto;
        try {
            userDto = JwtUtils.decryptJwt(token, UserDto.class);
        } catch (JwtException e) {
            throw new IsxcodeException("jwt解析失败 做处理");
        }

        // 生成用户authentication
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDto, token);

        // 权限附属
        getAuthenticationManager().authenticate(authentication);

        // 存进内存
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }
}

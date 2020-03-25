package com.ispong.oxygen.security;

import com.ispong.oxygen.core.encrypt.EncryptUtils;
import com.ispong.oxygen.module.user.entity.UserEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * jwt拦截器获取请求头中的 Authorization
 *
 * @author ispong
 * @since 0.0.1
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;

    private List<String> excludeUrlPaths;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, List<String> excludeUrlPaths) {

        this.authenticationManager = authenticationManager;
        this.excludeUrlPaths = excludeUrlPaths;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 获取请求头中得jwt值
        String authorization = request.getHeader("Authorization");

        // 如果jwt存在则默认为""
        if (authorization == null) {
            authorization = "";
        }

        try {
            // jwt解析工具,获取用户信息对象
            UserEntity userEntity = EncryptUtils.jwtDecrypt(authorization, UserEntity.class);
            // 创建临时身份对象
            AuthenticationToken authRequest = new AuthenticationToken(userEntity.getUserId(), "");
            // 进入认证管理中心,进行认证
            authenticationManager.authenticate(authRequest);
        }catch (Exception e){
            request.getRequestDispatcher("/userAuthException").forward(request, response);
        }

        // 放行
        doFilter(request, response, filterChain);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        Assert.notNull(request, "http请求不能为空");

        return excludeUrlPaths.stream().anyMatch(p -> new AntPathMatcher().match(p, request.getServletPath()));
    }

}

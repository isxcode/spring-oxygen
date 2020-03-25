package com.ispong.oxygen.security;

import com.ispong.oxygen.OxygenConstants;
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

        String authorization = request.getHeader("Authorization");

        if (authorization == null) {
            request.getRequestDispatcher(OxygenConstants.EXCEPTION_CONTROLLER + "?exception=" + "authorization is null").forward(request, response);
            return;
        }

        try {
            UserEntity userEntity = EncryptUtils.jwtDecrypt(authorization, UserEntity.class);
            AuthenticationToken authRequest = new AuthenticationToken(userEntity.getUserId(), "");
            authenticationManager.authenticate(authRequest);
        } catch (Exception e) {
            request.getRequestDispatcher(OxygenConstants.EXCEPTION_CONTROLLER + "?exception=" + "jwt authorization error").forward(request, response);
            return;
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

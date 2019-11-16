package com.isxcode.ispring.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * jwt 拦截器
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-16
 */
@Slf4j
@Component
public class JwtFilter extends OncePerRequestFilter {

    private final AntPathMatcher antPathMatcher;

    private Set<String> excludeUrlPatterns = new HashSet<>();

    public JwtFilter(){
        this.antPathMatcher = new AntPathMatcher();
    }

    public void addExcludeUrlPatterns(@NonNull String... excludeUrlPatterns) {

        Assert.notNull(excludeUrlPatterns, "excludeUrlPatterns is not null");
        Collections.addAll(this.excludeUrlPatterns, excludeUrlPatterns);
    }

    /**
     * 重新实现不拦截犯方法
     *
     * @since 2019-11-16
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        return excludeUrlPatterns.stream().anyMatch(p -> antPathMatcher.match(p, request.getServletPath()));
    }

    /**
     * 具体拦截的方法
     *
     * @since 2019-11-16
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {



        logger.info("拦截测试");
        doFilter(request, response, filterChain);
    }
}

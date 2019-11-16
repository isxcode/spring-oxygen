package com.isxcode.ispring.common;

import org.springframework.lang.NonNull;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * filter 基础类
 *
 * @author ispong
 * @date 2019-11-16
 * @version v0.1.0
 */
public abstract class BaseFilter extends OncePerRequestFilter {

    private final AntPathMatcher antPathMatcher;

    private Set<String> excludeUrlPatterns = new HashSet<>();

    protected BaseFilter() {
        this.antPathMatcher = new AntPathMatcher();
    }

    /**
     * 重新实现不拦截方法
     *
     * @since 2019-11-16
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {

        return excludeUrlPatterns.stream().anyMatch(p -> antPathMatcher.match(p, request.getServletPath()));
    }

    public void addExcludeUrlPatterns(@NonNull String... excludeUrlPatterns) {

        Assert.notNull(excludeUrlPatterns, "excludeUrlPatterns is not null");
        Collections.addAll(this.excludeUrlPatterns, excludeUrlPatterns);
    }
}

package com.isxcode.ispring.filter;

import com.isxcode.ispring.common.BaseFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * jwt 拦截器
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-16
 */
@Slf4j
@Component
public class JwtFilter extends BaseFilter {

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

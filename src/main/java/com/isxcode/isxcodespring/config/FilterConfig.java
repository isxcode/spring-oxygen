package com.isxcode.isxcodespring.config;

import com.isxcode.isxcodespring.filter.CorsFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * filter配置中心
 *
 * @author ispon
 * @since 6/28/2019
 * @version 1.0.0
 */
@Slf4j
@Configuration
public class FilterConfig {

    /**
     * 跨域过滤器配置
     *
     */
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter() {

        FilterRegistrationBean<CorsFilter> corsFilter = new FilterRegistrationBean<>();
        corsFilter.setOrder(Ordered.HIGHEST_PRECEDENCE + 9);
        corsFilter.setFilter(new CorsFilter());
        String[] filterPath = {
                "/*"
        };
        corsFilter.addUrlPatterns(filterPath);
        return corsFilter;
    }

}

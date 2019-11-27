package com.isxcode.ispring.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * 身份安全认证
 *
 * @author ispong
 * @date 2019-11-27
 * @version v0.1.0
 */
@Component
class SpringSecurityAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {

        // 如何集成spring


        // 返回用户userId
        return Optional.of("ispong");
    }
}
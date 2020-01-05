package com.isxcode.ispring.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 身份认证管理中心
 *
 * 可以实现多个AuthenticationProvider 去进行身份校验
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-03
 */
@Slf4j
public class AuthenticationManagerImpl implements AuthenticationManager {

    private AuthenticationProvider tokenAuthenticationProvider = new TokenAuthenticationProviderImpl();

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("认证管理");

        return tokenAuthenticationProvider.authenticate(authentication);
    }

}

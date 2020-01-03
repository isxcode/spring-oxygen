package com.isxcode.ispring.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * 权限管理中心
 * 
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-03
 */
@Slf4j
public class AuthenticationManagerImpl implements AuthenticationManager {

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("权限管理");
        return null;
    }
}

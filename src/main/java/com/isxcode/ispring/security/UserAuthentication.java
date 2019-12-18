package com.isxcode.ispring.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;


/**
 * 自定义用户认证对象
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-12-17
 */
public class UserAuthentication extends AbstractAuthenticationToken {

    private Object principal;
    private Object credentials;

    public UserAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {

        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}

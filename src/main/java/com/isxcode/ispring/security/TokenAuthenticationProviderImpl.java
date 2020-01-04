package com.isxcode.ispring.security;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 认证核心
 * 
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-03
 */
@Slf4j
public class TokenAuthenticationProviderImpl implements AuthenticationProvider {

    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    private UserDetailsServiceImpl userDetailsService;

    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserDetails test = userDetailsService.loadUserByUsername("test");
        log.info("比较身份");
        return new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), AUTHORITIES);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}

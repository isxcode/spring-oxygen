package com.isxcode.ispring.security;

import com.isxcode.ispring.exception.IsxcodeException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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

    private UserDetailsService userDetailsService = new UserDetailsServiceImpl();

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("开始认证身份");

        // 查询真实的你
        UserDetails userDetails = userDetailsService.loadUserByUsername("admin");
        if (userDetails == null) {
            throw new IsxcodeException("用户不存在");
        }

        UserAuthenticationToken userAuthenticationToken = new UserAuthenticationToken(userDetails.getUsername(), "",
                authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));

        // 授权
        SecurityContextHolder.getContext().setAuthentication(userAuthenticationToken);
        // 检查用户
        return userAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }

}

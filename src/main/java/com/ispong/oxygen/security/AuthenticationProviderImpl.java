package com.ispong.oxygen.security;

import com.ispong.oxygen.exception.AuthException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.ArrayList;
import java.util.List;

/**
 * 权限处理器,直接返回一个用户
 * 授权一定要加 ROLE_
 *
 * example ROLE_USER
 * //@Secured({"ROLE_USER"})
 * //@PreAuthorize("hasRole('USER')")
 *
 * @author ispong
 * @since 0.0.1
 */
public class AuthenticationProviderImpl implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public AuthenticationProviderImpl(UserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<GrantedAuthority>();

    private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();

    static {
        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 获取userDetail
        UserDetails userDetail = userDetailsService.loadUserByUsername(authentication.getPrincipal().toString());

        if (userDetail == null) {
            throw new AuthException("用户不存在");
        }

        // 用户赋权
        AuthenticationToken authenticationToken = new AuthenticationToken(userDetail.getUsername(), "", authoritiesMapper.mapAuthorities(userDetail.getAuthorities()));
        authenticationToken.setDetails(authentication.getDetails());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return authenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}

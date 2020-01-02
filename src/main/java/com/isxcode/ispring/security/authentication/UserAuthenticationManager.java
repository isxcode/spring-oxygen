package com.isxcode.ispring.security.authentication;

import com.isxcode.ispring.model.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户权限控制器
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-12-16
 */
@Slf4j
public class UserAuthenticationManager implements AuthenticationManager {

    /**
     * 普通成员
     */
    static final List<GrantedAuthority> USER = new ArrayList<>();

    /**
     * 系统管理员
     */
    static final List<GrantedAuthority> SYSTEM = new ArrayList<>();

    static {
        USER.add(new SimpleGrantedAuthority("USER"));
        SYSTEM.add(new SimpleGrantedAuthority("SYSTEM"));
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        log.info("身份认证");

        // 获取用户的权限
        if (((UserDto) authentication.getDetails()).getUsername().equals("123")) {

            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), USER);
        } else if (((UserDto) authentication.getDetails()).getUsername().equals("1234")) {
            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), SYSTEM);
        }

        throw new BadCredentialsException("Bad Credentials");
    }
}

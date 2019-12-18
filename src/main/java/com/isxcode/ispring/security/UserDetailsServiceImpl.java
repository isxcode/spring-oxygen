package com.isxcode.ispring.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * 身份认证
     *
     * @param
     * @return
     * @since 2019-12-18
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 身份认证
        log.info("身份认证");

        UserSecurityDetail userSecurityDetail = new UserSecurityDetail();
        userSecurityDetail.setPassword("123");
        userSecurityDetail.setUsername("123");
        userSecurityDetail.isAccountNonLocked();
//        userSecurityDetail.isAccountNonExpired()

        return userSecurityDetail;
    }
}

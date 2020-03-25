package com.ispong.oxygen.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 实现用户权限授权
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        if (userId.equals("ispong")) {
            return User.withUsername(userId).password("password").authorities(AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER")).build();
        }
        return null;
    }
}

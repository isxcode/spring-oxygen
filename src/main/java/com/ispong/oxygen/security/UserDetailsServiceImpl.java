package com.ispong.oxygen.security;

import com.ispong.oxygen.exception.AuthException;
import com.ispong.oxygen.module.user.UserService;
import com.ispong.oxygen.module.user.entity.UserEntity;
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

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {

        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        UserEntity userInfo = userService.getUserInfo(userId);

        if (userInfo != null) {
            return User.withUsername(userInfo.getAccount()).password(userInfo.getPassword()).authorities(AuthorityUtils.commaSeparatedStringToAuthorityList(userInfo.getAuthority())).build();
        }
        throw new AuthException("用户不存在");
    }

}

package com.isxcode.ispring.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 获取用户详情
 *
 * @author ispong
 * @version v0.1.0
 * @date 2020-01-02
 */
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        log.info("通过userId获取用户的UserDetails");
        // 数据库获取用户名 密码 权限 其他信息
        String username = "admin";
        String password = "admin";
        String authorityString = "admin";

        return new User(username, new BCryptPasswordEncoder().encode(password), AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString));
    }

}

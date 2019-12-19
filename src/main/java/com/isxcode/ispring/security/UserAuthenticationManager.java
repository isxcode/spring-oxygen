//package com.isxcode.ispring.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 用户权限控制器
// *
// * @author ispong
// * @version v0.1.0
// * @date 2019-12-16
// */
//@Slf4j
//public class UserAuthenticationManager implements AuthenticationManager {
//
//    static final List<GrantedAuthority> AUTHORITIES = new ArrayList<>();
//
//    static {
//        AUTHORITIES.add(new SimpleGrantedAuthority("ROLE_USER"));
//    }
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//
////        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("dd");
////        String authority = simpleGrantedAuthority.getAuthority();
//        log.info("身份认证进入");
//
//        // 判断权限是否
//        if (authentication.getName().equals(authentication.getCredentials())) {
//            AbstractAuthenticationToken userAuthentication = (AbstractAuthenticationToken) authentication;
//            return new UsernamePasswordAuthenticationToken(authentication.getName(), authentication.getCredentials(), AUTHORITIES);
//        }
//        throw new BadCredentialsException("Bad Credentials");
//    }
//}

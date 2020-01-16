//package com.isxcode.ispring.security;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.access.AccessDecisionManager;
//import org.springframework.security.access.AccessDeniedException;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.authentication.InsufficientAuthenticationException;
//import org.springframework.security.core.Authentication;
//
//import java.util.Collection;
//
///**
// * 权限认证中心
// *
// * @author ispong
// * @version v0.1.0
// * @date 2020-01-03
// */
//@Slf4j
//public class AccessDecisionManagerImpl implements AccessDecisionManager {
//
//    @Override
//    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
//
//        log.info("权限决策判断");
//    }
//
//    @Override
//    public boolean supports(ConfigAttribute attribute) {
//        return false;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return false;
//    }
//}

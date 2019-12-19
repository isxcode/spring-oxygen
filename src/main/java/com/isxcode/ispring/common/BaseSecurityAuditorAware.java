//package com.isxcode.ispring.common;
//
//import com.isxcode.ispring.security.UserSecurityDetail;
//import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import java.util.Optional;
//
///**
// * 身份安全认证
// *
// * @author ispong
// * @date 2019-11-27
// * @version v0.1.0
// */
//@Component
//class BaseSecurityAuditorAware implements AuditorAware<String> {
//
//    @Override
//    public Optional<String> getCurrentAuditor() {
//
//        return Optional.of(((UserSecurityDetail) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUserId());
//    }
//}
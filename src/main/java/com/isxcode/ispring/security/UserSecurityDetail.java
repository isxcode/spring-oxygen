//package com.isxcode.ispring.security;
//
//import lombok.Data;
////import org.springframework.security.core.GrantedAuthority;
////import org.springframework.security.core.userdetails.UserDetails;
//
//import java.util.Collection;
//
///**
// * 用户安全对象
// *
// * <p>
// *     UserDetails的功能属性太少需要扩展
// * </p>
// *
// * @author ispong
// * @version v0.1.0
// * @date 2019-12-16
// */
//@Data
//public class UserSecurityDetail implements UserDetails {
//
//    private String userId;
//
//    private String username;
//
//    private String password;
//
//    /**
//     * 应用类型  DING_DING 钉钉   WE_CHAT 微信  WEB 网页端  MOBILE 移动端
//     */
//    private String appType;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}

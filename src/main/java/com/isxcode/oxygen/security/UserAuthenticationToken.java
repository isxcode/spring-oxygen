//package com.isxcode.ispring.security;
//
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.SpringSecurityCoreVersion;
//
//import java.util.Collection;
//
///**
// * 替代UsernamePasswordAuthenticationToken
// *
// * @author ispong
// * @version v0.1.0
// * @date 2020-01-04
// */
//public class UserAuthenticationToken extends AbstractAuthenticationToken {
//
//    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;
//
//    private final Object principal;
//
//    private Object credentials;
//
//    /**
//     * 初始化一个userDetail对象
//     *
//     * @author ispong
//     * @version v0.1.0
//     * @date 2020-01-04
//     */
//    public UserAuthenticationToken(Object principal, Object credentials) {
//        super(null);
//        this.principal = principal;
//        this.credentials = credentials;
//        setAuthenticated(false);
//    }
//
//    /**
//     * This constructor should only be used by <code>AuthenticationManager</code> or
//     * <code>AuthenticationProvider</code> implementations that are satisfied with
//     * producing a trusted (i.e. {@link #isAuthenticated()} = <code>true</code>)
//     * authentication token.
//     *
//     * @param principal
//     * @param credentials
//     * @param authorities
//     */
//    public UserAuthenticationToken(Object principal, Object credentials,
//                                               Collection<? extends GrantedAuthority> authorities) {
//        super(authorities);
//        this.principal = principal;
//        this.credentials = credentials;
//        super.setAuthenticated(true); // must use super, as we override
//    }
//
//    // ~ Methods
//    // ========================================================================================================
//
//    @Override
//    public Object getCredentials() {
//        return this.credentials;
//    }
//
//    @Override
//    public Object getPrincipal() {
//        return this.principal;
//    }
//
//    @Override
//    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
//        if (isAuthenticated) {
//            throw new IllegalArgumentException(
//                    "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
//        }
//
//        super.setAuthenticated(false);
//    }
//
//    @Override
//    public void eraseCredentials() {
//        super.eraseCredentials();
//        credentials = null;
//    }
//}

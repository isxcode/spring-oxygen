package com.isxcode.oxygen.flysql.common;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class OxygenHolder {

    public static String getUserUuid() {

        if (SecurityContextHolder.getContext() == null ||
            SecurityContextHolder.getContext().getAuthentication() == null ||
            SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {

            return "anonymous";
        }

        return String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }

    public static void setUserUuid(String userUuid) {

        Authentication authentication = new Authentication() {
            @Override
            public boolean equals(Object another) {

                if (!(another instanceof Authentication)) {
                    return false;
                }

                Authentication auth = (Authentication) another;
                return auth.getName().equals(this.getName());
            }

            @Override
            public String toString() {
                return null;
            }

            @Override
            public int hashCode() {
                return 0;
            }

            @Override
            public String getName() {
                return null;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public Object getCredentials() {
                return null;
            }

            @Override
            public Object getDetails() {
                return null;
            }

            @Override
            public Object getPrincipal() {
                return userUuid;
            }

            @Override
            public boolean isAuthenticated() {
                return false;
            }

            @Override
            public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

            }
        };

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}

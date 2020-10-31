package com.ispong.oxygen.flysql.common;

import org.springframework.security.core.context.SecurityContextHolder;

public class OxygenHolder {

    public static String getUserUuid() {

        if (SecurityContextHolder.getContext() == null ||
            SecurityContextHolder.getContext().getAuthentication() == null ||
            SecurityContextHolder.getContext().getAuthentication().getPrincipal() == null) {

            return "anonymous";
        }

        return String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
    }
}

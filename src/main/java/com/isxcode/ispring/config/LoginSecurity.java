package com.isxcode.ispring.config;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class LoginSecurity extends AbstractAuthenticationProcessingFilter {

    protected LoginSecurity(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    protected LoginSecurity(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {

        if (!request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        /*
        // 添加验证码校验功能
        String captcha = request.getParameter("captcha");
        if (!checkCaptcha(captcha)) {
            throw new AuthenticationException("Invalid captcha!");
        }
        */
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        username = Objects.isNull(username) ? "" : username.trim();
        password = Objects.isNull(password) ? "" : password;

        UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                username, password);
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
        return this.getAuthenticationManager().authenticate(authRequest);

    }
}

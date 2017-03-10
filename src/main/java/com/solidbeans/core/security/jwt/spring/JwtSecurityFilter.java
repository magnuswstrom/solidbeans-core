package com.solidbeans.core.security.jwt.spring;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static com.solidbeans.core.security.jwt.JwtUtil.jwt;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class JwtSecurityFilter extends GenericFilterBean {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationEntryPoint authenticationFailureEntryPoint;

    public JwtSecurityFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationFailureEntryPoint) {
        this.authenticationManager = authenticationManager;
        this.authenticationFailureEntryPoint = authenticationFailureEntryPoint;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;

        try {
            String jwt = jwt(httpRequest);

            if (Objects.nonNull(jwt)) {
                Authentication authRequest = new PreAuthenticatedAuthenticationToken(new JwtPrincipal(jwt, httpRequest), null);
                Authentication authResult = authenticationManager.authenticate(authRequest);

                SecurityContextHolder.getContext().setAuthentication(authResult);
            }

            chain.doFilter(request, response);
        }
        catch (AuthenticationException e) {
            SecurityContextHolder.clearContext();
            authenticationFailureEntryPoint.commence(httpRequest, (HttpServletResponse)response, e);
        }
    }
}

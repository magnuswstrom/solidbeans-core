package com.solidbeans.core.security.jwt.spring;

import com.solidbeans.core.security.jwt.Parts;
import org.springframework.http.HttpHeaders;
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
import java.util.Enumeration;
import java.util.Objects;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class SecurityJwtFilter extends GenericFilterBean {

    private final AuthenticationManager authenticationManager;
    private final AuthenticationEntryPoint authenticationFailureEntryPoint;

    public SecurityJwtFilter(AuthenticationManager authenticationManager, AuthenticationEntryPoint authenticationFailureEntryPoint) {
        this.authenticationManager = authenticationManager;
        this.authenticationFailureEntryPoint = authenticationFailureEntryPoint;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;

        try {
            String jwt = jwt(httpRequest);

            if (Objects.nonNull(jwt)) {
                Authentication authRequest = new PreAuthenticatedAuthenticationToken(new Principal(jwt, httpRequest), null);
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

    private String jwt(HttpServletRequest httpServletRequest) {
        String start = "Bearer ";
        Enumeration<String> headerValues = httpServletRequest.getHeaders(HttpHeaders.AUTHORIZATION);

        while(headerValues.hasMoreElements()) {
            String headerValue = headerValues.nextElement();

            if(headerValue != null) {
                if(Parts.isJwt(headerValue)) {
                    return headerValue.substring(start.length());
                }
            }
        }

        return null;
    }
}

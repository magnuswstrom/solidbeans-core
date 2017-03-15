package com.solidbeans.core.security.jwt.spring;

import javax.servlet.http.HttpServletRequest;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
final class JwtPrincipal {

    private final String jwt;
    private final HttpServletRequest httpRequest;

    public JwtPrincipal(String jwt, HttpServletRequest httpRequest) {
        this.jwt = jwt;
        this.httpRequest = httpRequest;
    }

    public String getJwt() {
        return jwt;
    }

    public HttpServletRequest getHttpRequest() {
        return httpRequest;
    }
}

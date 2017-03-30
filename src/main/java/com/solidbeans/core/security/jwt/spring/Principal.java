package com.solidbeans.core.security.jwt.spring;

import javax.servlet.http.HttpServletRequest;

/**
 * Principal that holds both JWT and request
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
final class Principal {

    private final String jwt;
    private final HttpServletRequest httpRequest;

    public Principal(String jwt, HttpServletRequest httpRequest) {
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

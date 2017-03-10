/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.security.jwt.spring;

import org.springframework.security.core.AuthenticationException;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class JwtException extends AuthenticationException {

    public JwtException(String message) {
        super(message);
    }

    public JwtException(String message, Throwable cause) {
        super(message, cause);
    }

}

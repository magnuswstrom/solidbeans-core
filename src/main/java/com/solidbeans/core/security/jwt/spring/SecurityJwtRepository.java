/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.security.jwt.spring;

import com.solidbeans.core.security.jwt.algorithm.Algorithm;
import com.solidbeans.core.security.jwt.claims.Claims;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public interface SecurityJwtRepository<T> {

    /**
     * Should return secret depending on algorithm to match {@link com.solidbeans.core.security.jwt.algorithm.AlgorithmProvider#verify(byte[], byte[], Object)}
     *
     * @param algorithm Algorithm to get secret for
     * @param claims Claims that shall be verified
     * @param httpServletRequest The request
     * @return Algorithm secret
     */
    Object algorithmSecret(Algorithm algorithm, Claims<T> claims, HttpServletRequest httpServletRequest);

    /**
     * Returns roles for claims. Claims are verified at this point and can be trusted.
     *
     * @param claims Claims to get roles for
     * @param httpServletRequest The request
     * @return All roles that claims has access to
     */
    List<String> roles(Claims<T> claims, HttpServletRequest httpServletRequest);

}

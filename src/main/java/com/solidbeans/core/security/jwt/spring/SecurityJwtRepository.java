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
    
    Object algorithmSecret(Algorithm algorithm, Claims<T> claims, HttpServletRequest httpServletRequest);

    List<String> roles(Claims<T> claims, HttpServletRequest httpServletRequest);

}

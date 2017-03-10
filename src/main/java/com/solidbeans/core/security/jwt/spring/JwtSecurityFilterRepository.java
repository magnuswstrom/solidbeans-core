/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.security.jwt.spring;

import com.solidbeans.core.security.jwt.JwtClaims;

import javax.servlet.http.HttpServletRequest;
import java.security.PublicKey;
import java.util.List;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public interface JwtSecurityFilterRepository<T> {
    
    String algorithmHmacShaSecret(JwtClaims<T> claims, HttpServletRequest httpServletRequest);

    PublicKey algorithmRsaShaPublicKey(JwtClaims<T> claims, HttpServletRequest httpServletRequest);

    List<String> roles(JwtClaims<T> claims, HttpServletRequest httpServletRequest);

}

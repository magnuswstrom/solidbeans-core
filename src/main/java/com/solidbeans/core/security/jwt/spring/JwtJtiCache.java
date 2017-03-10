/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.security.jwt.spring;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class JwtJtiCache {

    private final Cache<String, String> cache;

    public JwtJtiCache(JwtConfig config) {
        checkNotNull(config);

        this.cache = CacheBuilder.newBuilder()
                .maximumSize(config.getCacheMaxSize())
                .expireAfterAccess(config.getCacheTime(), config.getCacheTimeUnit())
                .build();
    }
    
    public boolean exists(String jti) {
        return cache.getIfPresent(jti) != null;
    }
    
    public void put(String jti) {
        cache.put(jti, jti);
    }
}

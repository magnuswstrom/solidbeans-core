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
 * JTI cache to handle multiple request with same JTI
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
final class JtiCache {

    private final Cache<String, String> cache;

    private JtiCache(ClaimsConfig config) {
        this.cache = CacheBuilder.newBuilder()
                .maximumSize(config.getCacheMaxSize())
                .expireAfterAccess(config.getCacheTime(), config.getCacheTimeUnit())
                .build();
    }

    public static JtiCache createJtiCache(ClaimsConfig config) {
        checkNotNull(config);
        return new JtiCache(config);
    }

    public boolean exists(String jti) {
        return cache.getIfPresent(jti) != null;
    }
    
    public void put(String jti) {
        cache.put(jti, jti);
    }
}

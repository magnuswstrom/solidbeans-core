/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.security.jwt;

import com.solidbeans.core.security.jwt.algorithm.Algorithm;
import com.solidbeans.core.security.jwt.algorithm.AlgorithmFactory;
import com.solidbeans.core.security.jwt.algorithm.AlgorithmProvider;
import com.solidbeans.core.security.jwt.claims.Claims;

import java.security.GeneralSecurityException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class Signer {

    private final Algorithm algorithm;

    private Signer(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * Creates Signer instance from algorithm
     *
     * @param algorithm Algorithm for this instance
     * @return Signer instance
     */
    public static Signer fromAlg(Algorithm algorithm) {
        checkNotNull(algorithm);
        return new Signer(algorithm);
    }

    /**
     * Signs claims using algorithm for this instance. Secret should conform to {@link AlgorithmProvider#sign(byte[], Object)} depending on chosen algorithm
     *
     * @param claims Claims to sign
     * @param secret Secret to sign claims with
     * @param <T> Own claims
     * @return Signature
     * @throws GeneralSecurityException If signing claims fails
     */
    public <T> String sign(Claims<T> claims, Object secret) throws GeneralSecurityException {
        Parts parts = Parts.fromAlg(algorithm, claims);
        AlgorithmProvider algorithmProvider = AlgorithmFactory.algorithmProvider(algorithm);

        byte[] signature = algorithmProvider.sign(parts.getHeaderAndClaims(), secret);
        checkNotNull(signature);

        return Parts.fromJwtParts(parts, signature).getJwt();
    }
}

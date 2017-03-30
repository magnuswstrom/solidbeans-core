/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.security.jwt;

import com.solidbeans.core.security.jwt.algorithm.Algorithm;
import com.solidbeans.core.security.jwt.algorithm.AlgorithmFactory;
import com.solidbeans.core.security.jwt.algorithm.AlgorithmProvider;

import java.security.GeneralSecurityException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class Verifier {

    private final Algorithm algorithm;

    private Verifier(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    public static Verifier fromAlg(Algorithm algorithm) {
        checkNotNull(algorithm);
        return new Verifier(algorithm);
    }

    public boolean verify(String jwt, Object secret) throws GeneralSecurityException {
        Parts parts = Parts.fromJwt(jwt);
        AlgorithmProvider algorithmProvider = AlgorithmFactory.algorithmProvider(algorithm);

        return algorithmProvider.verify(parts.getHeaderAndClaims(), parts.getSignature(), secret);
    }
}

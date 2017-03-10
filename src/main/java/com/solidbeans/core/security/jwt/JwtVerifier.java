/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.security.jwt;

import com.solidbeans.core.security.algorithm.Algorithm;
import com.solidbeans.core.security.algorithm.AlgorithmHmacSha;
import com.solidbeans.core.security.algorithm.AlgorithmRsaSha;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SignatureException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class JwtVerifier {

    private final AlgorithmHmacSha algorithmHmacSha;
    private final AlgorithmRsaSha algorithmRsaSha;
    private final String secret;
    private final PublicKey publicKey;

    private JwtVerifier(Algorithm algorithm, String secret) throws NoSuchAlgorithmException {
        this.algorithmHmacSha = AlgorithmHmacSha.fromAlg(algorithm);
        this.algorithmRsaSha = null;
        this.secret = secret;
        this.publicKey = null;
    }

    private JwtVerifier(Algorithm algorithm, PublicKey publicKey) throws NoSuchAlgorithmException {
        this.algorithmHmacSha = null;
        this.algorithmRsaSha = AlgorithmRsaSha.fromAlg(algorithm);
        this.secret = null;
        this.publicKey = publicKey;
    }

    public static JwtVerifier fromAlg(Algorithm algorithm, String secret) throws NoSuchAlgorithmException {
        checkNotNull(secret);
        checkArgument(algorithm == Algorithm.HS256 || algorithm == Algorithm.HS384 || algorithm == Algorithm.HS512);
        return new JwtVerifier(algorithm, secret);
    }

    public static JwtVerifier fromAlg(Algorithm algorithm, PublicKey publicKey) throws NoSuchAlgorithmException {
        checkNotNull(publicKey);
        checkArgument(algorithm == Algorithm.RS256 || algorithm == Algorithm.RS384 || algorithm == Algorithm.RS512);
        return new JwtVerifier(algorithm, publicKey);
    }

    public <T> boolean verify(String jwt) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        JwtParts parts = JwtParts.fromJwt(jwt);

        if(algorithmHmacSha != null) {
            return algorithmHmacSha.verify(parts.getHeaderAndClaims(), parts.getSignature(), secret);
        }
        else if(algorithmRsaSha != null) {
            return algorithmRsaSha.verify(parts.getHeaderAndClaims(), parts.getSignature(), publicKey);
        }

        return false;
    }
}

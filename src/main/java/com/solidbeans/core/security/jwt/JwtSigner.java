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
import java.security.PrivateKey;
import java.security.SignatureException;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class JwtSigner {

    private final Algorithm algorithm;
    private final AlgorithmHmacSha algorithmHmacSha;
    private final AlgorithmRsaSha algorithmRsaSha;
    private final String secret;
    private final PrivateKey privateKey;

    private JwtSigner(Algorithm algorithm, String secret) throws NoSuchAlgorithmException {
        this.algorithm = algorithm;
        this.algorithmHmacSha = AlgorithmHmacSha.fromAlg(algorithm);
        this.algorithmRsaSha = null;
        this.secret = secret;
        this.privateKey = null;
    }

    private JwtSigner(Algorithm algorithm, PrivateKey privateKey) throws NoSuchAlgorithmException {
        this.algorithm = algorithm;
        this.algorithmHmacSha = null;
        this.algorithmRsaSha = AlgorithmRsaSha.fromAlg(algorithm);
        this.secret = null;
        this.privateKey = privateKey;
    }

    public static JwtSigner fromAlg(Algorithm algorithm, String secret) throws NoSuchAlgorithmException {
        checkNotNull(secret);
        checkArgument(algorithm == Algorithm.HS256 || algorithm == Algorithm.HS384 || algorithm == Algorithm.HS512);
        return new JwtSigner(algorithm, secret);
    }

    public static JwtSigner fromAlg(Algorithm algorithm, PrivateKey privateKey) throws NoSuchAlgorithmException {
        checkNotNull(privateKey);
        checkArgument(algorithm == Algorithm.RS256 || algorithm == Algorithm.RS384 || algorithm == Algorithm.RS512);
        return new JwtSigner(algorithm, privateKey);
    }

    public <T> String sign(JwtClaims<T> claims) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        JwtParts parts = JwtParts.fromAlg(algorithm, claims);
        byte[] signature = null;

        if(algorithmHmacSha != null) {
            signature = algorithmHmacSha.sign(parts.getHeaderAndClaims(), secret);
        }
        else if(algorithmRsaSha != null) {
            signature = algorithmRsaSha.sign(parts.getHeaderAndClaims(), privateKey);
        }

        checkNotNull(signature);

        return JwtParts.fromJwtParts(parts, signature).getJwt();
    }
}

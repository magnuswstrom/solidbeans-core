/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.security.jwt.algorithm;

import com.solidbeans.core.util.SolidUtil;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.GeneralSecurityException;
import java.util.Arrays;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class AlgorithmHmacShaProvider implements AlgorithmProvider {

    private final Mac hmacSha;

    private AlgorithmHmacShaProvider(Algorithm algorithm) throws GeneralSecurityException {
        this.hmacSha = algorithm.mac();
    }

    /**
     * Returns a HMAC SHA provider matching algorithm
     *
     * @param algorithm Algorithm to get provider for
     * @return HMAC SHA algorithm provider
     * @throws GeneralSecurityException If instantiating HMAC SHA provider fails
     */
    static AlgorithmHmacShaProvider fromAlg(Algorithm algorithm) throws GeneralSecurityException {
        checkNotNull(algorithm);
        return new AlgorithmHmacShaProvider(algorithm);
    }

    /**
     * Implementation of {@link AlgorithmProvider#sign(byte[], Object)}
     */
    public byte[] sign(byte[] data, Object secret) throws GeneralSecurityException {
        checkNotNull(data);
        checkNotNull(secret);
        checkArgument(secret instanceof String || secret instanceof byte[]);

        byte[] secretAsBytes = (secret instanceof String ? SolidUtil.Encoding.utf8((String)secret) : (byte[])secret);
        hmacSha.init(new SecretKeySpec(secretAsBytes, hmacSha.getAlgorithm()));

        return hmacSha.doFinal(data);
    }

    /**
     * Implementation of {@link AlgorithmProvider#verify(byte[], byte[], Object)}
     */
    public boolean verify(byte[] data, byte[] signature, Object secret) throws GeneralSecurityException {
        checkNotNull(signature);

        byte[] dataSignature = sign(data, secret);
        return Arrays.equals(dataSignature, signature);
    }
}

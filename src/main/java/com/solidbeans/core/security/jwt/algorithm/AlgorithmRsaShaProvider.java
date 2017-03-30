/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.security.jwt.algorithm;

import com.solidbeans.core.util.SolidUtil;

import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class AlgorithmRsaShaProvider implements AlgorithmProvider {

    private final Signature signature;

    private AlgorithmRsaShaProvider(Algorithm algorithm) throws GeneralSecurityException {
        this.signature = algorithm.signature();
    }

    /**
     * Returns a RSA SHA provider matching algorithm
     *
     * @param algorithm Algorithm to get provider for
     * @return RSA SHA algorithm provider
     * @throws GeneralSecurityException If instantiating RSA SHA provider fails
     */
    static AlgorithmRsaShaProvider fromAlg(Algorithm algorithm) throws GeneralSecurityException {
        checkNotNull(algorithm);
        return new AlgorithmRsaShaProvider(algorithm);
    }

    /**
     * Implementation of {@link AlgorithmProvider#sign(byte[], Object)}
     */
    public byte[] sign(byte[] data, Object secret) throws GeneralSecurityException {
        checkNotNull(data);
        checkNotNull(secret);
        checkArgument(secret instanceof String || secret instanceof PrivateKey);

        PrivateKey privateKey = (secret instanceof String ? SolidUtil.Security.privateRsaKey((String)secret) : (PrivateKey)secret);

        signature.initSign(privateKey);
        signature.update(data);

        return signature.sign();
    }

    /**
     * Implementation of {@link AlgorithmProvider#verify(byte[], byte[], Object)}
     */
    public boolean verify(byte[] data, byte[] signature, Object secret) throws GeneralSecurityException {
        checkNotNull(data);
        checkNotNull(signature);
        checkNotNull(secret);
        checkArgument(secret instanceof String || secret instanceof PublicKey);

        PublicKey publicKey = (secret instanceof String ? SolidUtil.Security.publicRsaKey((String)secret) : (PublicKey)secret);

        this.signature.initVerify(publicKey);
        this.signature.update(data);

        return this.signature.verify(signature);
    }
}

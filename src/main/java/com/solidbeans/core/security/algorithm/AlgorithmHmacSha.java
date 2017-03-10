/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.security.algorithm;

import com.solidbeans.core.util.UUIDGenerator;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.solidbeans.core.util.Encodings.utf8;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class AlgorithmHmacSha {

    private final Mac hmacSha;

    private AlgorithmHmacSha(Algorithm algorithm) throws NoSuchAlgorithmException {
        this.hmacSha = algorithm.getMac();
    }

    public static AlgorithmHmacSha fromAlg(Algorithm algorithm) throws NoSuchAlgorithmException {
        checkNotNull(algorithm);
        return new AlgorithmHmacSha(algorithm);
    }
        
    public byte[] sign(byte[] data, String secret) throws InvalidKeyException {
        checkNotNull(data);
        checkNotNull(secret);

        hmacSha.init(new SecretKeySpec(utf8(secret), hmacSha.getAlgorithm()));

        return hmacSha.doFinal(data);
    }

    public boolean verify(byte[] data, byte[] signature, String secret) throws InvalidKeyException {
        checkNotNull(data);
        checkNotNull(signature);
        checkNotNull(secret);

        byte[] dataSignature = sign(data, secret);
        return Arrays.equals(dataSignature, signature);
    }

    public static String randomSecret(Algorithm algorithm) {
        switch(algorithm) {
            case HS256:
                return UUIDGenerator.uuid(false);
            case HS384:
                return UUIDGenerator.uuid(false) + UUIDGenerator.uuid(false).substring(0, 16);
            case HS512:
                return UUIDGenerator.uuid(false) + UUIDGenerator.uuid(false);
            default:
                return null;
        }
    }
}

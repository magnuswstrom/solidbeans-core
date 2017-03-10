/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.security.algorithm;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.solidbeans.core.util.B64.decode;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class AlgorithmRsaSha {
    
    public enum KeySize {
        KS1024, KS2048
    }

    private final Signature signature;

    private AlgorithmRsaSha(Algorithm algorithm) throws NoSuchAlgorithmException {
        this.signature = algorithm.getSignature();
    }

    public static AlgorithmRsaSha fromAlg(Algorithm algorithm) throws NoSuchAlgorithmException {
        checkNotNull(algorithm);
        return new AlgorithmRsaSha(algorithm);
    }

    public byte[] sign(byte[] data, PrivateKey privateKey) throws InvalidKeyException, SignatureException {
        checkNotNull(data);
        checkNotNull(privateKey);

        signature.initSign(privateKey);
        signature.update(data);

        return signature.sign();
    }

    public boolean verify(byte[] data, byte[] signature, PublicKey publicKey) throws InvalidKeyException, SignatureException {
        checkNotNull(data);
        checkNotNull(signature);
        checkNotNull(publicKey);

        this.signature.initVerify(publicKey);
        this.signature.update(data);

        return this.signature.verify(signature);
    }
    
    public static PublicKey publicKey(String publicKeySpec) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(decode(publicKeySpec)));
    }
    
    public static PrivateKey privateKey(String privateKeySpec) throws InvalidKeySpecException, NoSuchAlgorithmException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decode(privateKeySpec)));
    }
    
    public KeyPair randomKeyPair(KeySize keySize) throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        switch(keySize) {
            case KS1024:
                keyPairGenerator.initialize(1024);
                return keyPairGenerator.generateKeyPair();
            case KS2048:
                keyPairGenerator.initialize(2048);
                return keyPairGenerator.generateKeyPair();
            default:
                return null;
        }
    }
}

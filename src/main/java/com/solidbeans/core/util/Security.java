package com.solidbeans.core.util;

import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public class Security {

    public enum ShaAlgorithm {
        SHA256, SHA384, SHA512
    }

    public enum RsaKeySize {
        KS1024, KS2048
    }

    Security() {
    }

    /**
     * Creates a random secret that could be used when working with HS256, HS384, HS512 algorithms in JWT i.e.
     *
     * @param shaAlgorithm Which SHA length secret should match
     * @return Random secret
     */
    public byte[] randomShaSecret(ShaAlgorithm shaAlgorithm) {
        switch(shaAlgorithm) {
            case SHA256:
                return SolidUtil.Encoding.utf8(SolidUtil.Uuid.uuid(true));
            case SHA384:
                return SolidUtil.Encoding.utf8(SolidUtil.Uuid.uuid(true) + SolidUtil.Uuid.uuid(true).substring(0, 16));
            case SHA512:
                return SolidUtil.Encoding.utf8(SolidUtil.Uuid.uuid(true) + SolidUtil.Uuid.uuid(true));
            default:
                return null;
        }
    }

    /**
     * Transforms a public key specification into a RSA public key.
     *
     * @param publicKeySpec Key specification
     * @return RSA public key
     * @throws GeneralSecurityException If anything goes wrong in transformation
     */
    public PublicKey publicRsaKey(String publicKeySpec) throws GeneralSecurityException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(new X509EncodedKeySpec(SolidUtil.Base64.decode(publicKeySpec)));
    }

    /**
     * Transforms a private key specification into a RSA private key.
     *
     * @param privateKeySpec Key specification
     * @return RSA private key
     * @throws GeneralSecurityException If anything goes wrong in transformation
     */
    public PrivateKey privateRsaKey(String privateKeySpec) throws GeneralSecurityException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(SolidUtil.Base64.decode(privateKeySpec)));
    }

    /**
     * Creates a random RSA key pair that could be used when working with RS256, RS384, RS512 algorithms in JWT i.e.
     * @param rsaKeySize Key size
     * @return RSA key pair
     * @throws GeneralSecurityException If anything goes wrong generating key pair
     */
    public KeyPair randomRsaKeyPair(RsaKeySize rsaKeySize) throws GeneralSecurityException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        switch(rsaKeySize) {
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

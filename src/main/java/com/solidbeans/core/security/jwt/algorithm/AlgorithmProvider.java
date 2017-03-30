package com.solidbeans.core.security.jwt.algorithm;

import java.security.GeneralSecurityException;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public interface AlgorithmProvider {

    /**
     * Signs data using secret. Depending on algorithm secret can be different thing.
     * For HMACSHA algorithms it can be byte[] or UTF-8 String secret.
     * For RSASHA algorithms it can be UTF-8 String private key specification or {@link java.security.PrivateKey}.
     *
     * @param data Data to sign
     * @param secret Secret to sign data with
     * @return Signature
     * @throws GeneralSecurityException If signing data fails
     */
    byte[] sign(byte[] data, Object secret) throws GeneralSecurityException;

    /**
     * Verifies signature using secret. Depending on algorithm secret can be different thing.
     * For HMACSHA algorithms it can be byte[] or UTF-8 String secret.
     * For RSASHA algorithms it can be UTF-8 String public key specification or {@link java.security.PublicKey}.
     *
     * @param data Data to use when verifying
     * @param signature Signature to verify
     * @param secret Secret to use verifying
     * @return If signature is valid
     * @throws GeneralSecurityException If verifying fails
     */
    boolean verify(byte[] data, byte[] signature, Object secret) throws GeneralSecurityException;

}

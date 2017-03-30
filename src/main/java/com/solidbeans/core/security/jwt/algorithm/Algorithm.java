/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.security.jwt.algorithm;

import javax.crypto.Mac;
import java.security.GeneralSecurityException;
import java.security.Signature;

/**
 * Defines supported JWT algorithms
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public enum Algorithm {
    HS256("HmacSHA256"),
    HS384("HmacSHA384"),
    HS512("HmacSHA512"),
    RS256("SHA256withRSA"),
    RS384("SHA384withRSA"),
    RS512("SHA512withRSA");
    
    private final String implementationAlg;
    
    Algorithm(String implementationAlg) {
        this.implementationAlg = implementationAlg;
    }

    /**
     * Gets the public JWT algorithm name, like HS256 i.e
     *
     * @return Public JWT algorithm name
     */
    public String alg() {
        return name();
    }

    /**
     * Gets a message authentication code instance suited for this algorithm
     *
     * @return Message authentication code instance
     * @throws GeneralSecurityException If instantiating fails
     */
    Mac mac() throws GeneralSecurityException {
        return Mac.getInstance(implementationAlg);
    }

    /**
     * Gets a digital signature algorithm instance suited for this algorithm
     *
     * @return Digital signature algorithm instance
     * @throws GeneralSecurityException If instantiating fails
     */
    Signature signature() throws GeneralSecurityException {
        return Signature.getInstance(implementationAlg);
    }

    /**
     * Gets algorithm from public JWT algorithm string
     *
     * @param alg Public JWT algorithm string
     * @return Matching algorithm
     */
    public static Algorithm fromAlg(String alg) {
        for(Algorithm algorithm : values()) {
            if(algorithm.alg().equals(alg)) {
                return algorithm;
            }
        }
        
        throw new IllegalArgumentException("Unknown algorithm " + alg);
    }
}

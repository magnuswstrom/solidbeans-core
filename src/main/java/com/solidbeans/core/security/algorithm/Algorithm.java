/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.security.algorithm;

import javax.crypto.Mac;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;

/**
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

    public String getAlg() {
        return name();
    }

    public Mac getMac() throws NoSuchAlgorithmException {
        return Mac.getInstance(implementationAlg);
    }

    public Signature getSignature() throws NoSuchAlgorithmException {
        return Signature.getInstance(implementationAlg);
    }

    public static Algorithm fromAlg(String alg) {
        for(Algorithm algorithm : values()) {
            if(algorithm.getAlg().equals(alg)) {
                return algorithm;
            }
        }
        
        throw new IllegalArgumentException("Unknown algorithm " + alg);
    }
}

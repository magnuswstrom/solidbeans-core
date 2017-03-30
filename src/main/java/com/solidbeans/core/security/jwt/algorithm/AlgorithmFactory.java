package com.solidbeans.core.security.jwt.algorithm;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class AlgorithmFactory {

    private AlgorithmFactory() {
    }

    /**
     * Returns a algorithm provider for supplied algorithm
     *
     * @param algorithm To get provider for
     * @return Algorithm provider
     * @throws GeneralSecurityException If anything goes wrong instantiating algorithm provider
     */
    public static AlgorithmProvider algorithmProvider(Algorithm algorithm) throws GeneralSecurityException {
        checkNotNull(algorithm);

        switch(algorithm) {
            case HS256:
            case HS384:
            case HS512:
                return AlgorithmHmacShaProvider.fromAlg(algorithm);
            case RS256:
            case RS384:
            case RS512:
                return AlgorithmRsaShaProvider.fromAlg(algorithm);
            default:
                throw new InvalidAlgorithmParameterException(algorithm.alg() + " is not supported");
        }
    }
}

package com.solidbeans.core.security.jwt.claims;

import com.solidbeans.core.security.jwt.algorithm.Algorithm;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class Header {

    private final String typ;
    private final String alg;

    private Header(String typ, String alg) {
        this.typ = typ;
        this.alg = alg;
    }

    public static Header fromAlg(String alg) {
        checkNotNull(alg);
        return new Header("JWT", alg);
    }

    public static Header fromAlg(Algorithm algorithm) {
        checkNotNull(algorithm);
        return fromAlg(algorithm.alg());
    }

    public String getTyp() {
        return typ;
    }

    public String getAlg() {
        return alg;
    }
}

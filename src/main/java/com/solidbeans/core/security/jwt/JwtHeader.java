package com.solidbeans.core.security.jwt;

import com.solidbeans.core.security.algorithm.Algorithm;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class JwtHeader {

    private final String typ;
    private final String alg;

    private JwtHeader(String typ, String alg) {
        this.typ = typ;
        this.alg = alg;
    }

    public static JwtHeader fromAlg(String alg) {
        checkNotNull(alg);
        return new JwtHeader("JWT", alg);
    }

    public static JwtHeader fromAlg(Algorithm algorithm) {
        checkNotNull(algorithm);
        return fromAlg(algorithm.getAlg());
    }

    public String getTyp() {
        return typ;
    }

    public String getAlg() {
        return alg;
    }
}

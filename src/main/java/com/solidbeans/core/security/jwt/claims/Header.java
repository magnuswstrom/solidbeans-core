package com.solidbeans.core.security.jwt.claims;

import com.solidbeans.core.security.jwt.algorithm.Algorithm;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * For all standard properties see https://jwt.io/
 *
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
        return fromAlg("JWT", alg);
    }

    public static Header fromAlg(String typ, String alg) {
        checkNotNull(alg);
        checkNotNull(typ);
        return new Header(typ, alg);
    }

    public static Header fromAlg(Algorithm algorithm) {
        return fromAlg("JWT", algorithm);
    }

    public static Header fromAlg(String typ, Algorithm algorithm) {
        checkNotNull(algorithm);
        return fromAlg(typ, algorithm.alg());
    }

    public String getTyp() {
        return typ;
    }

    public String getAlg() {
        return alg;
    }
}

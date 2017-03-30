package com.solidbeans.core.security.jwt.claims;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class Claims<T> {

    private final String iss;
    private final String sub;
    private final String aud;
    private final Long exp;
    private final Long nbf;
    private final Long iat;
    private final String jti;
    private final T own;

    private Claims(String iss, String sub, String aud, Long exp, Long nbf, Long iat, String jti, T own) {
        this.iss = iss;
        this.sub = sub;
        this.aud = aud;
        this.exp = exp;
        this.nbf = nbf;
        this.iat = iat;
        this.jti = jti;
        this.own = own;
    }

    public static <T> Claims<T> fromClaims(String iss, String sub, String aud, Long exp, Long nbf, Long iat, String jti, T own) {
        return new Claims<>(iss, sub, aud, exp, nbf, iat, jti, own);
    }

    public String getIss() {
        return iss;
    }

    public String getSub() {
        return sub;
    }

    public String getAud() {
        return aud;
    }

    public Long getExp() {
        return exp;
    }

    public Long getNbf() {
        return nbf;
    }

    public Long getIat() {
        return iat;
    }

    public String getJti() {
        return jti;
    }

    public T getOwn() {
        return own;
    }
}

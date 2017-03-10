package com.solidbeans.core.security.jwt;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class JwtClaims<T> {

    private String iss;
    private String sub;
    private String aud;
    private Long exp;
    private Long nbf;
    private Long iat;
    private String jti;
    private T own;

    private JwtClaims(String iss, String sub, String aud, Long exp, Long nbf, Long iat, String jti, T own) {
        this.iss = iss;
        this.sub = sub;
        this.aud = aud;
        this.exp = exp;
        this.nbf = nbf;
        this.iat = iat;
        this.jti = jti;
        this.own = own;
    }

    public static <T> JwtClaims<T> fromClaims(String iss, String sub, String aud, Long exp, Long nbf, Long iat, String jti, T own) {
        return new JwtClaims<>(iss, sub, aud, exp, nbf, iat, jti, own);
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

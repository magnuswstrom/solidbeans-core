package com.solidbeans.core.security.jwt.claims;

/**
 * For all standard properties see https://jwt.io/
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class Claims<T> {

    private String iss;
    private String sub;
    private String aud;
    private Long exp;
    private Long nbf;
    private Long iat;
    private String jti;
    private T own;

    public String getIss() {
        return iss;
    }

    public void setIss(String iss) {
        this.iss = iss;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getAud() {
        return aud;
    }

    public void setAud(String aud) {
        this.aud = aud;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public Long getNbf() {
        return nbf;
    }

    public void setNbf(Long nbf) {
        this.nbf = nbf;
    }

    public Long getIat() {
        return iat;
    }

    public void setIat(Long iat) {
        this.iat = iat;
    }

    public String getJti() {
        return jti;
    }

    public void setJti(String jti) {
        this.jti = jti;
    }

    public T getOwn() {
        return own;
    }

    public void setOwn(T own) {
        this.own = own;
    }
}

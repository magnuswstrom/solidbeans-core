package com.solidbeans.core.security.jwt;

import com.solidbeans.core.security.algorithm.Algorithm;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.solidbeans.core.security.jwt.JwtUtil.isJwt;
import static com.solidbeans.core.util.B64.*;
import static com.solidbeans.core.util.Encodings.utf8;
import static com.solidbeans.core.util.Json.fromJson;
import static com.solidbeans.core.util.Json.toJson;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class JwtParts {

    private final String header;
    private final String claims;
    private final String signature;

    private JwtParts(String header, String claims, String signature) {
        this.header = header;
        this.claims = claims;
        this.signature = signature;
    }

    public static JwtParts fromJwt(String jwt) {
        checkArgument(isJwt(jwt));
        String[] parts = jwt.split("\\.");
        return new JwtParts(parts[0], parts[1], parts[2]);
    }

    public static JwtParts fromJwtParts(JwtParts parts, byte[] signature) {
        checkNotNull(parts);
        checkNotNull(signature);
        return new JwtParts(parts.getHeader(), parts.getClaims(), urlEncodeToString(signature, true));
    }

    public static <T> JwtParts fromAlg(Algorithm algorithm, JwtClaims<T> claims) {
        checkNotNull(claims);
        JwtHeader header = JwtHeader.fromAlg(algorithm);
        return new JwtParts(urlEncodeToString(toJson(header), true), urlEncodeToString(toJson(claims), true), null);
    }

    public Algorithm getAlgorithm() {
        JwtHeader header = getJwtHeader();
        return Algorithm.fromAlg(header.getAlg());
    }

    public String getHeader() {
        return header;
    }

    public String getClaims() {
        return claims;
    }

    public byte[] getHeaderAndClaims() {
        return utf8(header + "." + claims);
    }

    public String getJwt() {
        return header + "." + claims + "." + signature;
    }

    public JwtHeader getJwtHeader() {
        return fromJson(urlDecodeToString(header), JwtHeader.class);
    }

    public <T> JwtClaims<T> getJwtClaims(Class<JwtClaims<T>> claimsClass) {
        return fromJson(urlDecodeToString(claims), claimsClass);
    }

    public byte[] getSignature() {
        if(signature == null) {
            return null;
        }

        return urlDecode(signature);
    }
}

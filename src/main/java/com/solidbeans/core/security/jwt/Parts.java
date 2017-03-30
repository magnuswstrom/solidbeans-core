package com.solidbeans.core.security.jwt;

import com.solidbeans.core.security.jwt.algorithm.Algorithm;
import com.solidbeans.core.security.jwt.claims.Claims;
import com.solidbeans.core.security.jwt.claims.Header;
import com.solidbeans.core.util.SolidUtil;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class Parts {

    private final String header;
    private final String claims;
    private final String signature;

    private Parts(String header, String claims, String signature) {
        this.header = header;
        this.claims = claims;
        this.signature = signature;
    }

    public static boolean isJwt(String jwt) {
        checkNotNull(jwt);
        return jwt.matches("[^.]+\\.[^.]+\\.[^.]+");
    }

    public static Parts fromJwt(String jwt) {
        checkArgument(isJwt(jwt));
        String[] parts = jwt.split("\\.");
        return new Parts(parts[0], parts[1], parts[2]);
    }

    public static Parts fromJwtParts(Parts parts, byte[] signature) {
        checkNotNull(parts);
        checkNotNull(signature);
        return new Parts(parts.getHeader(), parts.getClaims(), SolidUtil.Base64.urlEncodeToString(signature, true));
    }

    public static <T> Parts fromAlg(Algorithm algorithm, Claims<T> claims) {
        checkNotNull(claims);
        Header header = Header.fromAlg(algorithm);
        return new Parts(SolidUtil.Base64.urlEncodeToString(SolidUtil.Json.toJson(header), true),
                SolidUtil.Base64.urlEncodeToString(SolidUtil.Json.toJson(claims), true), null);
    }

    public Algorithm getAlgorithm() {
        Header header = getJwtHeader();
        return Algorithm.fromAlg(header.getAlg());
    }

    public String getHeader() {
        return header;
    }

    public String getClaims() {
        return claims;
    }

    public byte[] getHeaderAndClaims() {
        return SolidUtil.Encoding.utf8(header + "." + claims);
    }

    public String getJwt() {
        return header + "." + claims + "." + signature;
    }

    public Header getJwtHeader() {
        return SolidUtil.Json.fromJson(SolidUtil.Base64.urlDecodeToString(header), Header.class);
    }

    public <T> Claims<T> getJwtClaims(Class<Claims<T>> claimsClass) {
        return SolidUtil.Json.fromJson(SolidUtil.Base64.urlDecodeToString(claims), claimsClass);
    }

    public byte[] getSignature() {
        if(signature == null) {
            return null;
        }

        return SolidUtil.Base64.urlDecode(signature);
    }
}

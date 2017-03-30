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

    /**
     * Creates instance from JWT
     *
     * @param jwt JWT data
     * @return Parts instance
     */
    public static Parts fromJwt(String jwt) {
        checkArgument(isJwt(jwt));
        String[] parts = jwt.split("\\.");
        return new Parts(parts[0], parts[1], parts[2]);
    }

    /**
     * Creates instance from Parts and signature
     *
     * @param parts Original Parts
     * @param signature Signature for parts
     * @return Parts instance
     */
    public static Parts fromJwtParts(Parts parts, byte[] signature) {
        checkNotNull(parts);
        checkNotNull(signature);
        return new Parts(parts.getHeader(), parts.getClaims(), SolidUtil.Base64.urlEncodeToString(signature, true));
    }

    /**
     * Creates instance from algorithm and claims
     *
     * @param algorithm Algorithm
     * @param claims Claims
     * @param <T> Own Claims
     * @return Parts instance
     */
    public static <T> Parts fromAlg(Algorithm algorithm, Claims<T> claims) {
        checkNotNull(claims);
        Header header = Header.fromAlg(algorithm);
        return new Parts(SolidUtil.Base64.urlEncodeToString(SolidUtil.Json.toJson(header), true),
                SolidUtil.Base64.urlEncodeToString(SolidUtil.Json.toJson(claims), true), null);
    }

    /**
     * Get algorithm part
     *
     * @return Algorithm part
     */
    public Algorithm getAlgorithm() {
        Header header = getHeaderAsEntity();
        return Algorithm.fromAlg(header.getAlg());
    }

    /**
     * Get header part
     *
     * @return Header part
     */
    public String getHeader() {
        return header;
    }

    /**
     * Get claims part
     *
     * @return Claims part
     */
    public String getClaims() {
        return claims;
    }

    /**
     * Get signature part
     *
     * @return Signature part
     */
    public byte[] getSignature() {
        if(signature == null) {
            return null;
        }

        return SolidUtil.Base64.urlDecode(signature);
    }

    /**
     * Get header and claims (header . claims)
     *
     * @return Headers and claims
     */
    public byte[] getHeaderAndClaims() {
        return SolidUtil.Encoding.utf8(header + "." + claims);
    }

    /**
     * Get complete JWT (header . claims . signature)
     *
     * @return JWT
     */
    public String getJwt() {
        return header + "." + claims + "." + signature;
    }

    /**
     * Get header as entity
     *
     * @return Header as entity
     */
    public Header getHeaderAsEntity() {
        return SolidUtil.Json.fromJson(SolidUtil.Base64.urlDecodeToString(header), Header.class);
    }

    /**
     * Get claims as entity
     *
     * @param claimsClass Claims class
     * @param <T> Own Claims
     * @return Claims as entity
     */
    public <T> Claims<T> getClaimsAsEntity(Class<Claims<T>> claimsClass) {
        return SolidUtil.Json.fromJson(SolidUtil.Base64.urlDecodeToString(claims), claimsClass);
    }

    /**
     * Tells if input data is valid JWT
     *
     * @param jwt Data to validate
     * @return If valid JWT or not
     */
    public static boolean isJwt(String jwt) {
        checkNotNull(jwt);
        return jwt.matches("[^.]+\\.[^.]+\\.[^.]+");
    }
}

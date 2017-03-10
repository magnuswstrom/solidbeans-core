package com.solidbeans.core.security.jwt.spring;

import com.solidbeans.core.security.algorithm.Algorithm;
import com.solidbeans.core.security.jwt.JwtClaims;
import com.solidbeans.core.security.jwt.JwtParts;
import com.solidbeans.core.security.jwt.JwtVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class JwtSecurityProvider<T> implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtSecurityProvider.class);

    private final JwtSecurityFilterRepository<T> repository;
    private final JwtConfig config;
    private final JwtJtiCache jtiCache;
    private final Class<JwtClaims<T>> claimsClass;

    public JwtSecurityProvider(JwtSecurityFilterRepository<T> repository, JwtConfig config, Class<JwtClaims<T>> claimsClass) {
        checkNotNull(repository);
        checkNotNull(config);

        this.repository = repository;
        this.config = config;
        this.jtiCache = new JwtJtiCache(config);
        this.claimsClass = claimsClass;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            checkNotNull(authentication);
            checkNotNull(authentication.getPrincipal());
            checkArgument(authentication.getPrincipal() instanceof JwtPrincipal);
            checkNotNull(authentication);

            JwtPrincipal principal = (JwtPrincipal)authentication.getPrincipal();
            JwtParts parts = JwtParts.fromJwt(principal.getJwt());
            JwtClaims<T> claims = parts.getJwtClaims(claimsClass);
            Algorithm algorithm = parts.getAlgorithm();

            authorizeAlgorithm(algorithm);
            authorizeClaims(claims, principal);
            authorizeSignature(algorithm, claims, principal);

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            List<String> roles = repository.roles(claims, principal.getHttpRequest());

            if(roles != null) {
                roles.forEach((role) -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
            }

            return new PreAuthenticatedAuthenticationToken(claims, null, authorities);
        }
        catch(Throwable e) {
            throw new JwtException("JWT authentication failed", e);
        }
    }

    private void authorizeAlgorithm(Algorithm algorithm) {
        checkArgument(algorithm == Algorithm.HS256 ||
            algorithm == Algorithm.HS384 ||
            algorithm == Algorithm.HS512 ||
            algorithm == Algorithm.RS256 ||
            algorithm == Algorithm.RS384 ||
            algorithm == Algorithm.RS512, "Invalid algorithm"
        );
    }

    private void authorizeClaims(JwtClaims<T> claims, JwtPrincipal principal) {
        checkNotNull(claims);

        if(config.isIssNotNull()) {
            checkNotNull(claims.getIss(), "ISS is null");

            if(config.isIssRequiredValid()) {
                boolean matchesValidIssuer = false;

                for (String validIssuer : config.getValidIssuers()) {
                    if(Objects.equals(claims.getIss(), validIssuer)) {
                        matchesValidIssuer = true;
                        break;
                    }
                }

                checkArgument(matchesValidIssuer, "ISS not valid");
            }
        }

        if(config.isSubNotNull()) {
            checkNotNull(claims.getSub(), "SUB is null");
        }

        if(config.isAudNotNull()) {
            checkNotNull(claims.getAud(), "AUD is null");
        }

        if(config.isExpNotNull()) {
            checkNotNull(claims.getExp(), "EXP is null");
            checkArgument(claims.getExp() > System.currentTimeMillis(), "JWT expired");
        }

        if(config.isNbfNotNull()) {
            checkNotNull(claims.getNbf(), "NBF is null");
            checkArgument(claims.getNbf() < System.currentTimeMillis(), "JWT not valid yet");
        }

        if(config.isIatNotNull()) {
            checkNotNull(claims.getIat(), "IAT is null");
        }

        if(config.isJtiNotNull()) {
            checkNotNull(claims.getJti(), "JTI is null");

            switch (config.getJtiType()) {
                case IP:
                    checkArgument(isIP(claims.getJti()), "JTI not valid IP format");
                    checkArgument(Objects.equals(principal.getHttpRequest().getRemoteAddr(), claims.getJti()), "JTI not valid IP");
                    break;
                case UUID:
                    checkArgument(isUUID(claims.getJti()), "JTI not valid UUID format");
                    checkArgument(!jtiCache.exists(claims.getJti()), "JTI already executed");
                    jtiCache.put(claims.getJti());
                    break;
                default:
                    break;
            }
        }
    }

    private void authorizeSignature(Algorithm algorithm, JwtClaims<T> claims, JwtPrincipal principal) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        JwtVerifier verifier;

        switch(algorithm) {
            case HS256:
            case HS384:
            case HS512:
                verifier = JwtVerifier.fromAlg(algorithm, repository.algorithmHmacShaSecret(claims, principal.getHttpRequest()));
                checkArgument(verifier.verify(principal.getJwt()), "Invalid signature");
                break;
            case RS256:
            case RS384:
            case RS512:
                verifier = JwtVerifier.fromAlg(algorithm, repository.algorithmRsaShaPublicKey(claims, principal.getHttpRequest()));
                checkArgument(verifier.verify(principal.getJwt()), "Invalid signature");
                break;
            default:
                throw new JwtException("Unsupported JWT algorithm " + algorithm);
        }
    }

    private boolean isIP(String ip) {
        return ip.matches("[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}") ||
                ip.matches("[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}:[0-9A-Za-z]{1,4}");
    }

    private boolean isUUID(String uuid) {
        return uuid.matches("[0-9A-Za-z]{8}-[0-9A-Za-z]{4}-[0-9A-Za-z]{4}-[0-9A-Za-z]{4}-[0-9A-Za-z]{12}");
    }

    @Override
    public boolean supports(Class<?> tokenClass) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(tokenClass);
    }
}

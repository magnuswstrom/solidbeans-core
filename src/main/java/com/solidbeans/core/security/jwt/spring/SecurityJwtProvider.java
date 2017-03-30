package com.solidbeans.core.security.jwt.spring;

import com.solidbeans.core.security.jwt.Parts;
import com.solidbeans.core.security.jwt.Verifier;
import com.solidbeans.core.security.jwt.algorithm.Algorithm;
import com.solidbeans.core.security.jwt.claims.Claims;
import com.solidbeans.core.util.SolidUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * JWT authentication provider
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class SecurityJwtProvider<T> implements AuthenticationProvider {

    private static final Logger log = LoggerFactory.getLogger(SecurityJwtProvider.class);

    private final SecurityJwtRepository<T> repository;
    private final ClaimsConfig config;
    private final JtiCache jtiCache;
    private final Class<Claims<T>> claimsClass;

    private SecurityJwtProvider(SecurityJwtRepository<T> repository, ClaimsConfig config, Class<Claims<T>> claimsClass) {
        this.repository = repository;
        this.config = config;
        this.jtiCache = JtiCache.createJtiCache(config);
        this.claimsClass = claimsClass;
    }

    public static <T> SecurityJwtProvider<T> createSecurityJwtProvider(SecurityJwtRepository<T> repository, ClaimsConfig config, Class<Claims<T>> claimsClass) {
        checkNotNull(repository);
        checkNotNull(config);
        checkNotNull(claimsClass);

        return new SecurityJwtProvider<>(repository, config, claimsClass);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            checkNotNull(authentication);
            checkNotNull(authentication.getPrincipal());
            checkArgument(authentication.getPrincipal() instanceof Principal);
            checkNotNull(authentication);

            Principal principal = (Principal)authentication.getPrincipal();
            Parts parts = Parts.fromJwt(principal.getJwt());
            Claims<T> claims = parts.getClaimsAsEntity(claimsClass);
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
            throw new SecurityJwtException("JWT authentication failed", e);
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

    private void authorizeClaims(Claims<T> claims, Principal principal) {
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
                    checkArgument(SolidUtil.Nio.isValidIp(claims.getJti()), "JTI not valid IP format");
                    checkArgument(Objects.equals(principal.getHttpRequest().getRemoteAddr(), claims.getJti()), "JTI not valid IP");
                    break;
                case UUID:
                    checkArgument(SolidUtil.Uuid.isUuid(claims.getJti()), "JTI not valid Uuid format");
                    checkArgument(!jtiCache.exists(claims.getJti()), "JTI already executed");
                    jtiCache.put(claims.getJti());
                    break;
                default:
                    break;
            }
        }
    }

    private void authorizeSignature(Algorithm algorithm, Claims<T> claims, Principal principal) throws GeneralSecurityException {
        Verifier verifier = Verifier.fromAlg(algorithm);
        checkArgument(verifier.verify(principal.getJwt(), repository.algorithmSecret(algorithm, claims, principal.getHttpRequest())), "Invalid signature");
    }

    @Override
    public boolean supports(Class<?> tokenClass) {
        return PreAuthenticatedAuthenticationToken.class.isAssignableFrom(tokenClass);
    }
}

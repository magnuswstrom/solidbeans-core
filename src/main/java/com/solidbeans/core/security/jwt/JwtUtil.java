package com.solidbeans.core.security.jwt;

import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class JwtUtil {

    private JwtUtil() {
    }

    public static boolean isJwt(String jwt) {
        checkNotNull(jwt);
        return jwt.matches("[^.]+\\.[^.]+\\.[^.]+");
    }

    public static String jwt(HttpServletRequest httpServletRequest) {
        String start = "Bearer ";
        Enumeration<String> headerValues = httpServletRequest.getHeaders(HttpHeaders.AUTHORIZATION);

        while(headerValues.hasMoreElements()) {
            String headerValue = headerValues.nextElement();

            if(headerValue != null) {
                if(isJwt(headerValue)) {
                    return headerValue.substring(start.length());
                }
            }
        }

        return null;
    }
}

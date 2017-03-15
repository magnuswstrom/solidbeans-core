package com.solidbeans.core.util;

import java.util.UUID;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class UUIDGenerator {

    private UUIDGenerator() {
    }

    public static String uuid() {
        return uuid(false);
    }

    public static String uuid(boolean removeDelimiter) {
        String uuid = UUID.randomUUID().toString();

        if(removeDelimiter) {
            return uuid.replaceAll("-", "");
        }

        return uuid;
    }
}

package com.solidbeans.core.util;

import java.util.UUID;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class UUIDGenerator {

    private UUIDGenerator() {
    }

    /**
     * Generates UUID with delimiter
     *
     * @return UUID with delimiter
     */
    public static String uuid() {
        return uuid(false);
    }

    /**
     * Generates UUID
     *
     * @param removeDelimiter Tells to exclude delimiter
     * @return UUID
     */
    public static String uuid(boolean removeDelimiter) {
        String uuid = UUID.randomUUID().toString();

        if(removeDelimiter) {
            return uuid.replaceAll("-", "");
        }

        return uuid;
    }
}

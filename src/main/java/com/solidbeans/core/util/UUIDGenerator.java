package com.solidbeans.core.util;

import java.util.UUID;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class UUIDGenerator {

    private UUIDGenerator() {
    }

    public static String uuid(boolean delimiter) {
        String uuid = UUID.randomUUID().toString();

        if(delimiter) {
            return uuid.replaceAll("-", "");
        }

        return uuid;
    }
}

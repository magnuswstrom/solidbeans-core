package com.solidbeans.core.util;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class Uuid {

    Uuid() {
    }

    /**
     * Generates Uuid with delimiter
     *
     * @return Uuid with delimiter
     */
    public String uuid() {
        return uuid(false);
    }

    /**
     * Generates Uuid
     *
     * @param removeDelimiter Tells to exclude delimiter
     * @return Uuid
     */
    public String uuid(boolean removeDelimiter) {
        String uuid = java.util.UUID.randomUUID().toString();

        if(removeDelimiter) {
            return uuid.replaceAll("-", "");
        }

        return uuid;
    }

    /**
     * Tells if supplied string is in uuid format
     *
     * @param uuid Uuid to validate
     * @return If in Uuid format or not
     */
    public boolean isUuid(String uuid) {
        checkNotNull(uuid, "Uuid is null");
        return uuid.matches("[0-9A-Za-z]{8}-?[0-9A-Za-z]{4}-?[0-9A-Za-z]{4}-?[0-9A-Za-z]{4}-?[0-9A-Za-z]{12}");
    }
}

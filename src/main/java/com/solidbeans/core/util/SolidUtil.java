package com.solidbeans.core.util;

/**
 * Solid Beans Util
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class SolidUtil {

    public static final Nio Nio;
    public static final Uuid Uuid;
    public static final Base64 Base64;
    public static final Encoding Encoding;
    public static final Json Json;
    public static final Security Security;

    static {
        Nio = new Nio();
        Uuid = new Uuid();
        Base64 = new Base64();
        Encoding = new Encoding();
        Json = new Json();
        Security = new Security();
    }
}

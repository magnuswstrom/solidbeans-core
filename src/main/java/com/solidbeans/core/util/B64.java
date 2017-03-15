/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.util;

import static com.solidbeans.core.util.Encodings.utf8;
import java.util.Base64;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class B64 {
        
    private B64() {
    }

    /**
     * Base64 encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 encode
     * @return Base64 encoded data
     */
    public static String encodeToString(String data) {
        return utf8(encode(data));
    }

    /**
     * Base64 encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 encode
     * @return Base64 encoded data
     */
    public static String encodeToString(byte[] data) {
        return utf8(encode(data));
    }

    /**
     * Base64 encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 encode
     * @return Base64 encoded data
     */
    public static byte[] encode(String data) {
        return encode(utf8(data));
    }

    /**
     * Base64 encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 encode
     * @return Base64 encoded data
     */
    public static byte[] encode(byte[] data) {
        return Base64.getEncoder().encode(data);
    }

    /**
     * Base64 decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 decode
     * @return Base64 decoded data
     */
    public static String decodeToString(String data) { return utf8(decode(data)); }

    /**
     * Base64 decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 decode
     * @return Base64 decoded data
     */
    public static String decodeToString(byte[] data) { return utf8(decode(data)); }

    /**
     * Base64 decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 decode
     * @return Base64 decoded data
     */
    public static byte[] decode(String data) {
        return decode(utf8(data));
    }

    /**
     * Base64 decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 decode
     * @return Base64 decoded data
     */
    public static byte[] decode(byte[] data) {
        return Base64.getDecoder().decode(data);
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     * Uses padding characters at the end of the encoded byte data
     *
     * @param data Data to base64 URL encode
     * @return Base64 URL encoded data
     */
    public static String urlEncodeToString(String data) {
        return urlEncodeToString(data, false);
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL encode
     * @param withoutPadding tells to not add any padding character at the end of the encoded byte data
     * @return Base64 URL encoded data
     */
    public static String urlEncodeToString(String data, boolean withoutPadding) {
        return utf8(urlEncode(data, withoutPadding));
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     * Uses padding characters at the end of the encoded byte data
     *
     * @param data Data to base64 URL encode
     * @return Base64 URL encoded data
     */
    public static String urlEncodeToString(byte[] data) {
        return urlEncodeToString(data, false);
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL encode
     * @param withoutPadding tells to not add any padding character at the end of the encoded byte data
     * @return Base64 URL encoded data
     */
    public static String urlEncodeToString(byte[] data, boolean withoutPadding) {
        return utf8(urlEncode(data, withoutPadding));
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     * Uses padding characters at the end of the encoded byte data
     *
     * @param data Data to base64 URL encode
     * @return Base64 URL encoded data
     */
    public static byte[] urlEncode(String data) {
        return urlEncode(data, false);
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL encode
     * @param withoutPadding tells to not add any padding character at the end of the encoded byte data
     * @return Base64 URL encoded data
     */
    public static byte[] urlEncode(String data, boolean withoutPadding) {
        return urlEncode(utf8(data), withoutPadding);
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     * Uses padding characters at the end of the encoded byte data
     *
     * @param data Data to base64 URL encode
     * @return Base64 URL encoded data
     */
    public static byte[] urlEncode(byte[] data) {
        return urlEncode(data, false);
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL encode
     * @param withoutPadding tells to not add any padding character at the end of the encoded byte data
     * @return Base64 URL encoded data
     */
    public static byte[] urlEncode(byte[] data, boolean withoutPadding) {
        Base64.Encoder encoder = Base64.getUrlEncoder();
        
        if(withoutPadding) {
            encoder = encoder.withoutPadding();
        }
        
        return encoder.encode(data);
    }

    /**
     * Base64 URL decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL decode
     * @return Base64 URL decoded data
     */
    public static String urlDecodeToString(String data) {
        return utf8(urlDecode(data));
    }

    /**
     * Base64 URL decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL decode
     * @return Base64 URL decoded data
     */
    public static String urlDecodeToString(byte[] data) {
        return utf8(urlDecode(data));
    }

    /**
     * Base64 URL decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL decode
     * @return Base64 URL decoded data
     */
    public static byte[] urlDecode(String data) {
        return urlDecode(utf8(data));
    }

    /**
     * Base64 URL decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL decode
     * @return Base64 URL decoded data
     */
    public static byte[] urlDecode(byte[] data) {
        return Base64.getUrlDecoder().decode(data);
    }        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.util;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class Base64 {
        
    Base64() {
    }

    /**
     * Base64 encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 encode
     * @return Base64 encoded data
     */
    public String encodeToString(String data) {
        return SolidUtil.Encoding.utf8(encode(data));
    }

    /**
     * Base64 encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 encode
     * @return Base64 encoded data
     */
    public String encodeToString(byte[] data) {
        return SolidUtil.Encoding.utf8(encode(data));
    }

    /**
     * Base64 encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 encode
     * @return Base64 encoded data
     */
    public byte[] encode(String data) {
        return encode(SolidUtil.Encoding.utf8(data));
    }

    /**
     * Base64 encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 encode
     * @return Base64 encoded data
     */
    public byte[] encode(byte[] data) {
        return java.util.Base64.getEncoder().encode(data);
    }

    /**
     * Base64 decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 decode
     * @return Base64 decoded data
     */
    public String decodeToString(String data) { return SolidUtil.Encoding.utf8(decode(data)); }

    /**
     * Base64 decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 decode
     * @return Base64 decoded data
     */
    public String decodeToString(byte[] data) { return SolidUtil.Encoding.utf8(decode(data)); }

    /**
     * Base64 decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 decode
     * @return Base64 decoded data
     */
    public byte[] decode(String data) {
        return decode(SolidUtil.Encoding.utf8(data));
    }

    /**
     * Base64 decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 decode
     * @return Base64 decoded data
     */
    public byte[] decode(byte[] data) {
        return java.util.Base64.getDecoder().decode(data);
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     * Uses padding characters at the end of the encoded byte data
     *
     * @param data Data to base64 URL encode
     * @return Base64 URL encoded data
     */
    public String urlEncodeToString(String data) {
        return urlEncodeToString(data, false);
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL encode
     * @param withoutPadding tells to not add any padding character at the end of the encoded byte data
     * @return Base64 URL encoded data
     */
    public String urlEncodeToString(String data, boolean withoutPadding) {
        return SolidUtil.Encoding.utf8(urlEncode(data, withoutPadding));
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     * Uses padding characters at the end of the encoded byte data
     *
     * @param data Data to base64 URL encode
     * @return Base64 URL encoded data
     */
    public String urlEncodeToString(byte[] data) {
        return urlEncodeToString(data, false);
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL encode
     * @param withoutPadding tells to not add any padding character at the end of the encoded byte data
     * @return Base64 URL encoded data
     */
    public String urlEncodeToString(byte[] data, boolean withoutPadding) {
        return SolidUtil.Encoding.utf8(urlEncode(data, withoutPadding));
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     * Uses padding characters at the end of the encoded byte data
     *
     * @param data Data to base64 URL encode
     * @return Base64 URL encoded data
     */
    public byte[] urlEncode(String data) {
        return urlEncode(data, false);
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL encode
     * @param withoutPadding tells to not add any padding character at the end of the encoded byte data
     * @return Base64 URL encoded data
     */
    public byte[] urlEncode(String data, boolean withoutPadding) {
        return urlEncode(SolidUtil.Encoding.utf8(data), withoutPadding);
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     * Uses padding characters at the end of the encoded byte data
     *
     * @param data Data to base64 URL encode
     * @return Base64 URL encoded data
     */
    public byte[] urlEncode(byte[] data) {
        return urlEncode(data, false);
    }

    /**
     * Base64 URL encodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL encode
     * @param withoutPadding tells to not add any padding character at the end of the encoded byte data
     * @return Base64 URL encoded data
     */
    public byte[] urlEncode(byte[] data, boolean withoutPadding) {
        java.util.Base64.Encoder encoder = java.util.Base64.getUrlEncoder();
        
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
    public String urlDecodeToString(String data) {
        return SolidUtil.Encoding.utf8(urlDecode(data));
    }

    /**
     * Base64 URL decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL decode
     * @return Base64 URL decoded data
     */
    public String urlDecodeToString(byte[] data) {
        return SolidUtil.Encoding.utf8(urlDecode(data));
    }

    /**
     * Base64 URL decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL decode
     * @return Base64 URL decoded data
     */
    public byte[] urlDecode(String data) {
        return urlDecode(SolidUtil.Encoding.utf8(data));
    }

    /**
     * Base64 URL decodes data using UTF-8 when transforming from string to bytes and vice versa.
     *
     * @param data Data to base64 URL decode
     * @return Base64 URL decoded data
     */
    public byte[] urlDecode(byte[] data) {
        return java.util.Base64.getUrlDecoder().decode(data);
    }        
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.util;

import java.nio.charset.StandardCharsets;

/**
 * Encoding wrapper
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class Encodings {
    
    private Encodings() {
    }

    public static byte[] usascii(String data) {
        return data.getBytes(StandardCharsets.US_ASCII);
    }

    public static String usascii(byte[] data) {
        return new String(data, StandardCharsets.US_ASCII);
    }

    public static byte[] iso88591(String data) {
        return data.getBytes(StandardCharsets.ISO_8859_1);
    }

    public static String iso88591(byte[] data) {
        return new String(data, StandardCharsets.ISO_8859_1);
    }

    public static byte[] utf8(String data) {
        return data.getBytes(StandardCharsets.UTF_8);
    }
    
    public static String utf8(byte[] data) {
        return new String(data, StandardCharsets.UTF_8);
    }

    public static byte[] utf16(String data) {
        return data.getBytes(StandardCharsets.UTF_16);
    }

    public static String utf16(byte[] data) {
        return new String(data, StandardCharsets.UTF_16);
    }

    public static byte[] utf16be(String data) {
        return data.getBytes(StandardCharsets.UTF_16BE);
    }

    public static String utf16be(byte[] data) {
        return new String(data, StandardCharsets.UTF_16BE);
    }

    public static byte[] utf16le(String data) {
        return data.getBytes(StandardCharsets.UTF_16LE);
    }

    public static String utf16le(byte[] data) {
        return new String(data, StandardCharsets.UTF_16LE);
    }

}

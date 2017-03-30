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
public final class Encoding {
    
    Encoding() {
    }

    /**
     * Gets bytes from string using US ASCII charset
     *
     * @param data String to get bytes for
     * @return Bytes with US ASCII charset
     */
    public byte[] usascii(String data) {
        return data.getBytes(StandardCharsets.US_ASCII);
    }

    /**
     * Creates string from bytes using US ASCII charset
     *
     * @param data Bytes to create string for
     * @return String with US ASCII charset
     */
    public String usascii(byte[] data) {
        return new String(data, StandardCharsets.US_ASCII);
    }

    /**
     * Gets bytes from string using ISO-8859-1 charset
     *
     * @param data String to get bytes for
     * @return Bytes with ISO-8859-1 charset
     */
    public byte[] iso88591(String data) {
        return data.getBytes(StandardCharsets.ISO_8859_1);
    }

    /**
     * Creates string from bytes using ISO-8859-1 charset
     *
     * @param data Bytes to create string for
     * @return String with ISO-8859-1 charset
     */
    public String iso88591(byte[] data) {
        return new String(data, StandardCharsets.ISO_8859_1);
    }

    /**
     * Gets bytes from string using UTF-8 charset
     *
     * @param data String to get bytes for
     * @return Bytes with UTF-8 charset
     */
    public byte[] utf8(String data) {
        return data.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * Creates string from bytes using UTF-8 charset
     *
     * @param data Bytes to create string for
     * @return String with UTF-8 charset
     */
    public String utf8(byte[] data) {
        return new String(data, StandardCharsets.UTF_8);
    }

    /**
     * Gets bytes from string using UTF-16 charset
     *
     * @param data String to get bytes for
     * @return Bytes with UTF-16 charset
     */
    public byte[] utf16(String data) {
        return data.getBytes(StandardCharsets.UTF_16);
    }

    /**
     * Creates string from bytes using UTF-16 charset
     *
     * @param data Bytes to create string for
     * @return String with UTF-16 charset
     */
    public String utf16(byte[] data) {
        return new String(data, StandardCharsets.UTF_16);
    }

    /**
     * Gets bytes from string using UTF-16BE charset
     *
     * @param data String to get bytes for
     * @return Bytes with UTF-16BE charset
     */
    public byte[] utf16be(String data) {
        return data.getBytes(StandardCharsets.UTF_16BE);
    }

    /**
     * Creates string from bytes using UTF-16BE charset
     *
     * @param data Bytes to create string for
     * @return String with UTF-16BE charset
     */
    public String utf16be(byte[] data) {
        return new String(data, StandardCharsets.UTF_16BE);
    }

    /**
     * Gets bytes from string using UTF-16LE charset
     *
     * @param data String to get bytes for
     * @return Bytes with UTF-16LE charset
     */
    public byte[] utf16le(String data) {
        return data.getBytes(StandardCharsets.UTF_16LE);
    }

    /**
     * Creates string from bytes using UTF-16LE charset
     *
     * @param data Bytes to create string for
     * @return String with UTF-16LE charset
     */
    public String utf16le(byte[] data) {
        return new String(data, StandardCharsets.UTF_16LE);
    }
}

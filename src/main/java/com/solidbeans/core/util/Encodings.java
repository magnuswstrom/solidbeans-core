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
    
    public static byte[] utf8(String data) {
        return data.getBytes(StandardCharsets.UTF_8);
    }
    
    public static String utf8(byte[] data) {
        return new String(data, StandardCharsets.UTF_8);
    }
}

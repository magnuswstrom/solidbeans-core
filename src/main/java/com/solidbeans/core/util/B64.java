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
        
    public static String encodeToString(String data) {
        return utf8(encode(utf8(data)));
    }
    
    public static String encodeToString(byte[] data) {
        return utf8(encode(data));
    }
    
    public static byte[] encode(String data) {
        return encode(utf8(data));
    }
    
    public static byte[] encode(byte[] data) {
        return Base64.getEncoder().encode(data);
    }

    public static byte[] decode(String data) {
        return decode(utf8(data));
    }
    
    public static byte[] decode(byte[] data) {
        return Base64.getDecoder().decode(data);
    }
    
    public static String urlEncodeToString(String data, boolean withoutPadding) {
        return utf8(urlEncode(utf8(data), withoutPadding));
    }

    public static String urlEncodeToString(byte[] data, boolean withoutPadding) {
        return utf8(urlEncode(data, withoutPadding));
    }
    
    public static byte[] urlEncode(String data, boolean withoutPadding) {
        return urlEncode(utf8(data), withoutPadding);
    }

    public static byte[] urlEncode(byte[] data, boolean withoutPadding) {
        Base64.Encoder encoder = Base64.getUrlEncoder();
        
        if(withoutPadding) {
            encoder = encoder.withoutPadding();
        }
        
        return encoder.encode(data);
    }

    public static String urlDecodeToString(String data) {
        return utf8(urlDecode(utf8(data)));
    }

    public static String urlDecodeToString(byte[] data) {
        return utf8(urlDecode(data));
    }        

    public static byte[] urlDecode(String data) {
        return urlDecode(utf8(data));
    }

    public static byte[] urlDecode(byte[] data) {
        return Base64.getUrlDecoder().decode(data);
    }        
}

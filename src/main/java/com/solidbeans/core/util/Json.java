/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.util;

import com.google.gson.Gson;

/**
 * A JSON implementation wrapper.
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class Json {
    
    private static final Gson gson = new Gson();
    
    private Json() {
    }
    
    public static <T> String toJson(T data) {
        return gson.toJson(data);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
}

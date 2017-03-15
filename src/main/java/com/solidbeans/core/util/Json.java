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

    /**
     * Transform object to JSON using underlying implementation.
     *
     * @param data Object to transform into JSON
     * @param <T> Type of object to transform
     * @return JSON string
     */
    public static <T> String toJson(T data) {
        return gson.toJson(data);
    }

    /**
     * Transform JSON to object using underlying implementation.
     *
     * @param json JSON string
     * @param classOfT Class definition of object to create
     * @param <T> Type of object to create
     * @return Transformed object from JSON
     */
    public static <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }
}

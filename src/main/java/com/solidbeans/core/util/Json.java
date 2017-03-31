/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Objects;

/**
 * A JSON implementation wrapper.
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class Json {

    public static class TypeAdapter<TA> {

        private Class<TA> classOfTA;
        private TA objectOfTA;

        private TypeAdapter(Class<TA> classOfTA, TA objectOfTA) {
            this.classOfTA = classOfTA;
            this.objectOfTA = objectOfTA;
        }

        public static <TA> TypeAdapter<TA> createTypeAdapter(Class<TA> classOfTA, TA objectOfTA) {
            return new TypeAdapter<>(classOfTA, objectOfTA);
        }

        public Class<TA> getClassOfTA() {
            return classOfTA;
        }

        public TA getObjectOfTA() {
            return objectOfTA;
        }
    }

    private final Gson gson = new Gson();
    
    Json() {
    }

    /**
     * Transform object to JSON using underlying implementation.
     *
     * @param data Object to transform into JSON
     * @param <T> Type of object to transform
     * @return JSON string
     */
    public <T> String toJson(T data) {
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
    public <T> T fromJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * Transform JSON to object using underlying implementation. Registers type adapters that handles sub types in JSON.
     *
     * @param json JSON string
     * @param classOfT Class definition of object to create
     * @param adapters Adapters to register
     * @param <T> Type of object to create
     * @return Transformed object from JSON
     */
    public <T> T fromJson(String json, Class<T> classOfT, TypeAdapter<?>... adapters) {
        GsonBuilder builder = new GsonBuilder();

        if(Objects.nonNull(adapters)) {
            for(TypeAdapter<?> adapter : adapters) {
                builder.registerTypeAdapter(adapter.getClassOfTA(), adapter.getObjectOfTA());
            }
        }

        return builder.create().fromJson(json, classOfT);
    }
}

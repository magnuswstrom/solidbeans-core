/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.async;

import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class ResponseEntitySupplier {

    public static <I, O> CompletableFuture<ResponseEntity<O>> supply(ArgumentSupplier<I, O> supplier, I input, ResponseEntityMapper<O> mapper) {
        return supply(AsyncProcessor.supplyAsync(), supplier, input, mapper);
    }

    public static <I, O> CompletableFuture<ResponseEntity<O>> supply(ArgumentSupplier<I, O> supplier, I input, ResponseEntityMapper<O> mapper, String threadPoolName) {
        return supply(AsyncProcessor.supplyAsync(threadPoolName), supplier, input, mapper);
    }

    public static <I, O> CompletableFuture<ResponseEntity<O>> supply(ArgumentSupplier<I, O> supplier, I input, ResponseEntityMapper<O> mapper, Executor executor) {
        return supply(AsyncProcessor.supplyAsync(executor), supplier, input, mapper);
    }

    private static <I, O> CompletableFuture<ResponseEntity<O>> supply(CompletableFuture<O> completableFuture, ArgumentSupplier<I, O> supplier, I input, ResponseEntityMapper<O> mapper) {
        return completableFuture.thenCompose((ignore) ->
            supplier.get(input)
                    .thenApply(mapper::mapSuccess)
                    .exceptionally(mapper::mapFailure)
        );
    }

    public static <I1, I2, O> CompletableFuture<ResponseEntity<O>> supply(TwoArgumentsSupplier<I1, I2, O> supplier, I1 input1, I2 input2, ResponseEntityMapper<O> mapper) {
        return supply(AsyncProcessor.supplyAsync(), supplier, input1, input2, mapper);
    }

    public static <I1, I2, O> CompletableFuture<ResponseEntity<O>> supply(TwoArgumentsSupplier<I1, I2, O> supplier, I1 input1, I2 input2, ResponseEntityMapper<O> mapper, String threadPoolName) {
        return supply(AsyncProcessor.supplyAsync(threadPoolName), supplier, input1, input2, mapper);
    }

    public static <I1, I2, O> CompletableFuture<ResponseEntity<O>> supply(TwoArgumentsSupplier<I1, I2, O> supplier, I1 input1, I2 input2, ResponseEntityMapper<O> mapper, Executor executor) {
        return supply(AsyncProcessor.supplyAsync(executor), supplier, input1, input2, mapper);
    }

    private static <I1, I2, O> CompletableFuture<ResponseEntity<O>> supply(CompletableFuture<O> completableFuture, TwoArgumentsSupplier<I1, I2, O> supplier, I1 input1, I2 input2, ResponseEntityMapper<O> mapper) {
        return completableFuture.thenCompose((ignore) ->
                supplier.get(input1, input2)
                        .thenApply(mapper::mapSuccess)
                        .exceptionally(mapper::mapFailure)
        );
    }
}

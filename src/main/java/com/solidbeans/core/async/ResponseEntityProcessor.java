/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.async;

import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Completable future helper with response entity support
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class ResponseEntityProcessor {

    private ResponseEntityProcessor() {
    }

    /**
     * Executes supplier in future using default thread pool, mapper transforms result into a response entity
     *
     * @param supplier Supplier to execute in future
     * @param mapper Transforms result into a response entity
     * @return Completable future reference
     */
    public static <O> CompletableFuture<ResponseEntity<O>> supply(Supplier<O> supplier, ResponseEntityMapper<O> mapper) {
        return supply(AsyncProcessor.supplyAsync(supplier), mapper);
    }

    /**
     * Executes supplier in future using named thread pool, mapper transforms result into a response entity
     *
     * @param supplier Supplier to execute in future
     * @param mapper Transforms result into a response entity
     * @param threadPoolName Name of thread pool to use, thread pool must be registered at {@link com.solidbeans.core.async.ThreadPools#registerThreadPool(String, ExecutorService)}
     * @return Completable future reference
     */
    public static <O> CompletableFuture<ResponseEntity<O>> supply(Supplier<O> supplier, ResponseEntityMapper<O> mapper, String threadPoolName) {
        return supply(AsyncProcessor.supplyAsync(supplier, threadPoolName), mapper);
    }

    /**
     * Executes supplier in future using provided executor, mapper transforms result into a response entity
     *
     * @param supplier Supplier to execute in future
     * @param mapper Transforms result into a response entity
     * @param executor Executor to use
     * @return Completable future reference
     */
    public static <O> CompletableFuture<ResponseEntity<O>> supply(Supplier<O> supplier, ResponseEntityMapper<O> mapper, Executor executor) {
        return supply(AsyncProcessor.supplyAsync(supplier, executor), mapper);
    }

    /**
     * Executes supplier that takes no argument in future using default thread pool, mapper transforms result into a response entity
     *
     * @param supplier Supplier to execute in future
     * @param mapper Transforms result into a response entity
     * @return Completable future reference
     */
    public static <O> CompletableFuture<ResponseEntity<O>> supply(ZeroArgumentSupplier<O> supplier, ResponseEntityMapper<O> mapper) {
        return supply(AsyncProcessor.supplyAsync(supplier), mapper);
    }

    /**
     * Executes supplier that takes no argument in future using named thread pool, mapper transforms result into a response entity
     *
     * @param supplier Supplier to execute in future
     * @param mapper Transforms result into a response entity
     * @param threadPoolName Name of thread pool to use, thread pool must be registered at {@link com.solidbeans.core.async.ThreadPools#registerThreadPool(String, ExecutorService)}
     * @return Completable future reference
     */
    public static <O> CompletableFuture<ResponseEntity<O>> supply(ZeroArgumentSupplier<O> supplier, ResponseEntityMapper<O> mapper, String threadPoolName) {
        return supply(AsyncProcessor.supplyAsync(supplier, threadPoolName), mapper);
    }

    /**
     * Executes supplier that takes no argument in future using provided executor, mapper transforms result into a response entity
     *
     * @param supplier Supplier to execute in future
     * @param mapper Transforms result into a response entity
     * @param executor Executor to use
     * @return Completable future reference
     */
    public static <O> CompletableFuture<ResponseEntity<O>> supply(ZeroArgumentSupplier<O> supplier, ResponseEntityMapper<O> mapper, Executor executor) {
        return supply(AsyncProcessor.supplyAsync(supplier, executor), mapper);
    }

    /**
     * Executes supplier that takes one argument in future using default thread pool, mapper transforms result into a response entity
     *
     * @param supplier Supplier to execute in future
     * @param input Input argument
     * @param mapper Transforms result into a response entity
     * @return Completable future reference
     */
    public static <I, O> CompletableFuture<ResponseEntity<O>> supply(OneArgumentSupplier<I, O> supplier, I input, ResponseEntityMapper<O> mapper) {
        return supply(AsyncProcessor.supplyAsync(supplier, input), mapper);
    }

    /**
     * Executes supplier that takes one argument in future using named thread pool, mapper transforms result into a response entity
     *
     * @param supplier Supplier to execute in future
     * @param input Input argument
     * @param mapper Transforms result into a response entity
     * @param threadPoolName Name of thread pool to use, thread pool must be registered at {@link com.solidbeans.core.async.ThreadPools#registerThreadPool(String, ExecutorService)}
     * @return Completable future reference
     */
    public static <I, O> CompletableFuture<ResponseEntity<O>> supply(OneArgumentSupplier<I, O> supplier, I input, ResponseEntityMapper<O> mapper, String threadPoolName) {
        return supply(AsyncProcessor.supplyAsync(supplier, input, threadPoolName), mapper);
    }

    /**
     * Executes supplier that takes one argument in future using provided executor, mapper transforms result into a response entity
     *
     * @param supplier Supplier to execute in future
     * @param input Input argument
     * @param mapper Transforms result into a response entity
     * @param executor Executor to use
     * @return Completable future reference
     */
    public static <I, O> CompletableFuture<ResponseEntity<O>> supply(OneArgumentSupplier<I, O> supplier, I input, ResponseEntityMapper<O> mapper, Executor executor) {
        return supply(AsyncProcessor.supplyAsync(supplier, input, executor), mapper);
    }

    /**
     * Executes supplier that takes two arguments in future using default thread pool, mapper transforms result into a response entity
     *
     * @param supplier Supplier to execute in future
     * @param input1 Input argument 1
     * @param input2 Input argument 2
     * @param mapper Transforms result into a response entity
     * @return Completable future reference
     */
    public static <I1, I2, O> CompletableFuture<ResponseEntity<O>> supply(TwoArgumentsSupplier<I1, I2, O> supplier, I1 input1, I2 input2, ResponseEntityMapper<O> mapper) {
        return supply(AsyncProcessor.supplyAsync(supplier, input1, input2), mapper);
    }

    /**
     * Executes supplier that takes two arguments in future using named thread pool, mapper transforms result into a response entity
     *
     * @param supplier Supplier to execute in future
     * @param input1 Input argument 1
     * @param input2 Input argument 2
     * @param mapper Transforms result into a response entity
     * @param threadPoolName Name of thread pool to use, thread pool must be registered at {@link com.solidbeans.core.async.ThreadPools#registerThreadPool(String, ExecutorService)}
     * @return Completable future reference
     */
    public static <I1, I2, O> CompletableFuture<ResponseEntity<O>> supply(TwoArgumentsSupplier<I1, I2, O> supplier, I1 input1, I2 input2, ResponseEntityMapper<O> mapper, String threadPoolName) {
        return supply(AsyncProcessor.supplyAsync(supplier, input1, input2, threadPoolName), mapper);
    }

    /**
     * Executes supplier that takes two arguments in future using provided executor, mapper transforms result into a response entity
     *
     * @param supplier Supplier to execute in future
     * @param input1 Input argument 1
     * @param input2 Input argument 2
     * @param mapper Transforms result into a response entity
     * @param executor Executor to use
     * @return Completable future reference
     */
    public static <I1, I2, O> CompletableFuture<ResponseEntity<O>> supply(TwoArgumentsSupplier<I1, I2, O> supplier, I1 input1, I2 input2, ResponseEntityMapper<O> mapper, Executor executor) {
        return supply(AsyncProcessor.supplyAsync(supplier, input1, input2, executor), mapper);
    }

    /**
     * Adds mapper step in completable future chain
     *
     * @param completableFuture Completable future to add mapper step to
     * @param mapper Mapper to be executed
     * @param <O> Type of output result
     * @return Completable future reference
     */
    private static <O> CompletableFuture<ResponseEntity<O>> supply(CompletableFuture<O> completableFuture, ResponseEntityMapper<O> mapper) {
        checkNotNull(mapper, "Mapper is null");

        return completableFuture
                .thenApply(mapper::mapSuccess)
                .exceptionally(mapper::mapFailure);
    }
}

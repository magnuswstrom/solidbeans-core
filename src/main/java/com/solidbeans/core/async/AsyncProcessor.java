/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Completable future helper
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class AsyncProcessor {

    private AsyncProcessor() {
    }

    /**
     * Executes runnable in future using default thread pool
     *
     * @param runnable Runnable to execute in future
     * @return Completable future reference
     */
    public static CompletableFuture<Void> runAsync(Runnable runnable) {
        return runAsync(runnable, ThreadPools.defaultThreadPool());
    }

    /**
     * Executes runnable in future using named thread pool
     *
     * @param runnable Runnable to execute in future
     * @param threadPoolName Name of thread pool to use, thread pool must be registered at {@link com.solidbeans.core.async.ThreadPools#registerThreadPool(String, ExecutorService)}
     * @return Completable future reference
     */
    public static CompletableFuture<Void> runAsync(Runnable runnable, String threadPoolName) {
        return runAsync(runnable, ThreadPools.threadPool(threadPoolName));
    }

    /**
     * Executes runnable in future using provided executor
     *
     * @param runnable Runnable to execute in future
     * @param executor Executor to use
     * @return Completable future reference
     */
    public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor) {
        checkNotNull(runnable, "Runnable is null");
        checkNotNull(executor, "Executor is null");

        return CompletableFuture.runAsync(runnable, executor);
    }

    /**
     * Executes supplier in future using default thread pool
     *
     * @param supplier Supplier to execute in future
     * @return Completable future reference
     */
    public static <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
        return supplyAsync(supplier, ThreadPools.defaultThreadPool());
    }

    /**
     * Executes supplier in future using named thread pool
     *
     * @param supplier Supplier to execute in future
     * @param threadPoolName Name of thread pool to use, thread pool must be registered at {@link com.solidbeans.core.async.ThreadPools#registerThreadPool(String, ExecutorService)}
     * @return Completable future reference
     */
    public static <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier, String threadPoolName) {
        return supplyAsync(supplier, ThreadPools.threadPool(threadPoolName));
    }

    /**
     * Executes supplier in future using provided executor
     *
     * @param supplier Supplier to execute in future
     * @param executor Executor to use
     * @return Completable future reference
     */
    public static <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier, Executor executor) {
        checkNotNull(supplier, "Supplier is null");
        checkNotNull(executor, "Executor is null");

        return CompletableFuture.supplyAsync(supplier, executor);
    }

    /**
     * Executes supplier in future using default thread pool
     *
     * @param supplier Supplier to execute in future
     * @return Completable future reference
     */
    public static <T> CompletableFuture<T> composeAsync(Supplier<CompletableFuture<T>> supplier) {
        return composeAsync(supplier, ThreadPools.defaultThreadPool());
    }

    /**
     * Executes supplier in future using named thread pool
     *
     * @param supplier Supplier to execute in future
     * @param threadPoolName Name of thread pool to use, thread pool must be registered at {@link com.solidbeans.core.async.ThreadPools#registerThreadPool(String, ExecutorService)}
     * @return Completable future reference
     */
    public static <T> CompletableFuture<T> composeAsync(Supplier<CompletableFuture<T>> supplier, String threadPoolName) {
        return composeAsync(supplier, ThreadPools.threadPool(threadPoolName));
    }

    /**
     * Executes supplier in future using provided executor
     *
     * @param supplier Supplier to execute in future
     * @param executor Executor to use
     * @return Completable future reference
     */
    public static <T> CompletableFuture<T> composeAsync(Supplier<CompletableFuture<T>> supplier, Executor executor) {
        checkNotNull(supplier, "Supplier is null");
        checkNotNull(executor, "Executor is null");

        return CompletableFuture.supplyAsync(AsyncProcessor::nullSupplier, executor).thenComposeAsync((ignore) -> supplier.get(), executor);
    }

    /**
     * Executes supplier that takes one argument in future using default thread pool
     *
     * @param supplier Supplier to execute in future
     * @param input Input argument
     * @return Completable future reference
     */
    public static <I, O> CompletableFuture<O> supplyAsync(Function<I, O> supplier, I input) {
        return supplyAsync(supplier, input, ThreadPools.defaultThreadPool());
    }

    /**
     * Executes supplier that takes one argument in future using named thread pool
     *
     * @param supplier Supplier to execute in future
     * @param input Input argument
     * @param threadPoolName Name of thread pool to use, thread pool must be registered at {@link com.solidbeans.core.async.ThreadPools#registerThreadPool(String, ExecutorService)}
     * @return Completable future reference
     */
    public static <I, O> CompletableFuture<O> supplyAsync(Function<I, O> supplier, I input, String threadPoolName) {
        return supplyAsync(supplier, input, ThreadPools.threadPool(threadPoolName));
    }

    /**
     * Executes supplier that takes one argument in future using provided executor
     *
     * @param supplier Supplier to execute in future
     * @param input Input argument
     * @param executor Executor to use
     * @return Completable future reference
     */
    public static <I, O> CompletableFuture<O> supplyAsync(Function<I, O> supplier, I input, Executor executor) {
        checkNotNull(supplier, "Supplier is null");

        return CompletableFuture.supplyAsync(AsyncProcessor::nullSupplier, executor).thenApplyAsync((ignore) -> supplier.apply(input), executor);
    }

    /**
     * Executes supplier that takes one argument in future using default thread pool
     *
     * @param supplier Supplier to execute in future
     * @param input Input argument
     * @return Completable future reference
     */
    public static <I, O> CompletableFuture<O> composeAsync(Function<I, CompletableFuture<O>> supplier, I input) {
        return composeAsync(supplier, input, ThreadPools.defaultThreadPool());
    }

    /**
     * Executes supplier that takes one argument in future using named thread pool
     *
     * @param supplier Supplier to execute in future
     * @param input Input argument
     * @param threadPoolName Name of thread pool to use, thread pool must be registered at {@link com.solidbeans.core.async.ThreadPools#registerThreadPool(String, ExecutorService)}
     * @return Completable future reference
     */
    public static <I, O> CompletableFuture<O> composeAsync(Function<I, CompletableFuture<O>> supplier, I input, String threadPoolName) {
        return composeAsync(supplier, input, ThreadPools.threadPool(threadPoolName));
    }

    /**
     * Executes supplier that takes one argument in future using provided executor
     *
     * @param supplier Supplier to execute in future
     * @param input Input argument
     * @param executor Executor to use
     * @return Completable future reference
     */
    public static <I, O> CompletableFuture<O> composeAsync(Function<I, CompletableFuture<O>> supplier, I input, Executor executor) {
        checkNotNull(supplier, "Supplier is null");

        return CompletableFuture.supplyAsync(AsyncProcessor::nullSupplier, executor).thenComposeAsync((ignore) -> supplier.apply(input), executor);
    }

    /**
     * Executes supplier that takes two arguments in future using default thread pool
     *
     * @param supplier Supplier to execute in future
     * @param input1 Input argument 1
     * @param input2 Input argument 2
     * @return Completable future reference
     */
    public static <I1, I2, O> CompletableFuture<O> supplyAsync(BiFunction<I1, I2, O> supplier, I1 input1, I2 input2) {
        return supplyAsync(supplier, input1, input2, ThreadPools.defaultThreadPool());
    }

    /**
     * Executes supplier that takes two arguments in future using named thread pool
     *
     * @param supplier Supplier to execute in future
     * @param input1 Input argument 1
     * @param input2 Input argument 2
     * @param threadPoolName Name of thread pool to use, thread pool must be registered at {@link com.solidbeans.core.async.ThreadPools#registerThreadPool(String, ExecutorService)}
     * @return Completable future reference
     */
    public static <I1, I2, O> CompletableFuture<O> supplyAsync(BiFunction<I1, I2, O> supplier, I1 input1, I2 input2, String threadPoolName) {
        return supplyAsync(supplier, input1, input2, ThreadPools.threadPool(threadPoolName));
    }

    /**
     * Executes supplier that takes two arguments in future using provided executor
     *
     * @param supplier Supplier to execute in future
     * @param input1 Input argument 1
     * @param input2 Input argument 2
     * @param executor Executor to use
     * @return Completable future reference
     */
    public static <I1, I2, O> CompletableFuture<O> supplyAsync(BiFunction<I1, I2, O> supplier, I1 input1, I2 input2, Executor executor) {
        checkNotNull(supplier, "Supplier is null");

        return CompletableFuture.supplyAsync(AsyncProcessor::nullSupplier, executor).thenApplyAsync((ignore) -> supplier.apply(input1, input2), executor);
    }

    /**
     * Executes supplier that takes two arguments in future using default thread pool
     *
     * @param supplier Supplier to execute in future
     * @param input1 Input argument 1
     * @param input2 Input argument 2
     * @return Completable future reference
     */
    public static <I1, I2, O> CompletableFuture<O> composeAsync(BiFunction<I1, I2, CompletableFuture<O>> supplier, I1 input1, I2 input2) {
        return composeAsync(supplier, input1, input2, ThreadPools.defaultThreadPool());
    }

    /**
     * Executes supplier that takes two arguments in future using named thread pool
     *
     * @param supplier Supplier to execute in future
     * @param input1 Input argument 1
     * @param input2 Input argument 2
     * @param threadPoolName Name of thread pool to use, thread pool must be registered at {@link com.solidbeans.core.async.ThreadPools#registerThreadPool(String, ExecutorService)}
     * @return Completable future reference
     */
    public static <I1, I2, O> CompletableFuture<O> composeAsync(BiFunction<I1, I2, CompletableFuture<O>> supplier, I1 input1, I2 input2, String threadPoolName) {
        return composeAsync(supplier, input1, input2, ThreadPools.threadPool(threadPoolName));
    }

    /**
     * Executes supplier that takes two arguments in future using provided executor
     *
     * @param supplier Supplier to execute in future
     * @param input1 Input argument 1
     * @param input2 Input argument 2
     * @param executor Executor to use
     * @return Completable future reference
     */
    public static <I1, I2, O> CompletableFuture<O> composeAsync(BiFunction<I1, I2, CompletableFuture<O>> supplier, I1 input1, I2 input2, Executor executor) {
        checkNotNull(supplier, "Supplier is null");

        return CompletableFuture.supplyAsync(AsyncProcessor::nullSupplier, executor).thenComposeAsync((ignore) -> supplier.apply(input1, input2), executor);
    }

    /**
     * Null supplier implementation
     *
     * @return null
     */
    private static Void nullSupplier() {
        return null;
    }
}

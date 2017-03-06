/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.function.Supplier;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class AsyncProcessor {
    
    public static CompletableFuture<Void> runAsync(Runnable runnable) {
        return runAsync(runnable, ThreadPools.defaultThreadPool());
    }

    public static CompletableFuture<Void> runAsync(Runnable runnable, String threadPoolName) {
        return runAsync(runnable, ThreadPools.threadPool(threadPoolName));
    }

    public static CompletableFuture<Void> runAsync(Runnable runnable, Executor executor) {
        return CompletableFuture.runAsync(runnable, executor);
    }

    public static <T> CompletableFuture<T> supplyAsync() {
        return supplyAsync(() -> null, ThreadPools.defaultThreadPool());
    }

    public static <T> CompletableFuture<T> supplyAsync(String threadPoolName) {
        return supplyAsync(() -> null, ThreadPools.threadPool(threadPoolName));
    }

    public static <T> CompletableFuture<T> supplyAsync(Executor executor) {
        return supplyAsync(() -> null, executor);
    }

    public static <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier) {
        return supplyAsync(supplier, ThreadPools.defaultThreadPool());
    }

    public static <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier, String threadPoolName) {
        return supplyAsync(supplier, ThreadPools.threadPool(threadPoolName));
    }

    public static <T> CompletableFuture<T> supplyAsync(Supplier<T> supplier, Executor executor) {
        return CompletableFuture.supplyAsync(supplier, executor);
    }
}

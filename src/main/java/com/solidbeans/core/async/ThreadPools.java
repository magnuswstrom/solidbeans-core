/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.async;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Thread pool manager
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class ThreadPools {

    private static Map<String, Executor> threadPools;

    static {
        threadPools = new HashMap<>();
    }

    private ThreadPools() {
    }

    /**
     * Reurns the system default thread pool {@link ForkJoinPool#commonPool()}
     *
     * @return System default thread pool
     */
    public static Executor defaultThreadPool() {
        return ForkJoinPool.commonPool();
    }

    /**
     * Returns a registered thread pool with given name, thread pool must have been registered with {@link ThreadPools#registerThreadPool(String, Executor)}
     *
     * @param name Thread pool name
     * @return Thread pool
     */
    public static Executor threadPool(String name) {
        checkNotNull(name, "Thread pool name is null");

        return threadPools.get(name);
    }

    /**
     * Registers a thread pool with a given name. Use {@link java.util.concurrent.Executors} to create different types of executors
     *
     * @param name Thread pool name
     * @param executor Thread pool executor
     */
    public static void registerThreadPool(String name, Executor executor) {
        checkNotNull(name, "Thread pool name is null");
        checkNotNull(executor, "Thread pool executor is null");

        threadPools.put(name, executor);
    }
}

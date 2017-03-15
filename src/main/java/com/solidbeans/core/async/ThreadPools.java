/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.async;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Thread pool manager
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class ThreadPools {

    private static final Map<String, ExecutorService> threadPools;

    static {
        threadPools = new HashMap<>();
    }

    private ThreadPools() {
    }

    /**
     * Reurns the system default thread pool {@link ForkJoinPool#commonPool()}
     *
     * @return System default thread pool executor service
     */
    public static ExecutorService defaultThreadPool() {
        return ForkJoinPool.commonPool();
    }

    /**
     * Returns a registered thread pool with given name, thread pool must have been registered with {@link ThreadPools#registerThreadPool(String, ExecutorService)}
     *
     * @param name Thread pool name
     * @return Thread pool executor service
     */
    public static ExecutorService threadPool(String name) {
        checkNotNull(name, "Thread pool name is null");

        return threadPools.get(name);
    }

    /**
     * Registers a thread pool with a given name. Use {@link java.util.concurrent.Executors} to create different types of executor services
     *
     * @param name Thread pool name
     * @param executorService Thread pool executor service
     */
    public static void registerThreadPool(String name, ExecutorService executorService) {
        checkNotNull(name, "Thread pool name is null");
        checkNotNull(executorService, "Thread pool executor service is null");

        threadPools.put(name, executorService);
    }

    /**
     * See {@link ExecutorService#shutdown()}
     */
    public static void shutdown() {
        threadPools.forEach((name, executorService) -> executorService.shutdown());
    }

    /**
     * See {@link ExecutorService#shutdownNow()}
     */
    public static void shutdownNow() {
        threadPools.forEach((name, executorService) -> executorService.shutdownNow());
    }

    /**
     * See {@link ExecutorService#awaitTermination(long, TimeUnit)}
     */
    public static void awaitTermination(long timeout, TimeUnit unit) {
        threadPools.forEach((name, executorService) -> {
            try {
                executorService.awaitTermination(timeout, unit);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * See {@link ExecutorService#isShutdown()}
     */
    public static boolean isShutdown() {
        for(ExecutorService executorService : threadPools.values()) {
            if(!executorService.isShutdown()) {
                return false;
            }
        }

        return true;
    }

    /**
     * See {@link ExecutorService#isTerminated()}
     */
    public static boolean isTerminated() {
        for(ExecutorService executorService : threadPools.values()) {
            if(!executorService.isTerminated()) {
                return false;
            }
        }

        return true;
    }
}

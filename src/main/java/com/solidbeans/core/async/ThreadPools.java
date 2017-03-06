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

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public final class ThreadPools {

    private static Map<String, Executor> threadPools;

    static {
        threadPools = new HashMap<>();
    }

    public static Executor defaultThreadPool() {
        return ForkJoinPool.commonPool();
    }

    public static Executor threadPool(String name) {
        return threadPools.get(name);
    }

    public static void registerThreadPool(String name, Executor executor) {
        threadPools.put(name, executor);
    }
}

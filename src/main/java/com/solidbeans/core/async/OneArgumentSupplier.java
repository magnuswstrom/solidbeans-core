/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.async;

import java.util.concurrent.CompletableFuture;

/**
 * Supplier with one argument
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public interface OneArgumentSupplier<I, O> {

    /**
     * Supplies output result with one argument in
     *
     * @param input Input argument
     * @return Completable future reference
     */
    CompletableFuture<O> get(I input);
    
}

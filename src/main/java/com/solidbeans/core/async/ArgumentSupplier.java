/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.async;

import java.util.concurrent.CompletableFuture;

/**
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public interface ArgumentSupplier<I, O> {
    
    CompletableFuture<O> get(I input);
    
}

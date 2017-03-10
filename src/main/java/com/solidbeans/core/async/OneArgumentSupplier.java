/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.solidbeans.core.async;

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
     * @return Output result
     */
    O get(I input);
    
}

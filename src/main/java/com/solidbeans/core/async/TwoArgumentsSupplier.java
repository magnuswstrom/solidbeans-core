package com.solidbeans.core.async;

import java.util.concurrent.CompletableFuture;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public interface TwoArgumentsSupplier<I1, I2, O> {

    CompletableFuture<O> get(I1 input1, I2 input2);

}

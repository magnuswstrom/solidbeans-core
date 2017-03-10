package com.solidbeans.core.async;

/**
 * Supplier with two arguments
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public interface TwoArgumentsSupplier<I1, I2, O> {

    /**
     * Supplies output result with two arguments in
     *
     * @param input1 Input argument 1
     * @param input2 Input argument 2
     * @return Output result
     */
    O get(I1 input1, I2 input2);

}

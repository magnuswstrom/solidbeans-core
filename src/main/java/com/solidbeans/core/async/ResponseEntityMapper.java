package com.solidbeans.core.async;

import org.springframework.http.ResponseEntity;

/**
 * Mapper for encapsulating output result into a response entity
 *
 * @author magnus.wahlstrom@solidbeans.com
 */
public interface ResponseEntityMapper<O> {

    /**
     * Map a output result object into a response entity
     *
     * @param output Output result
     * @return Response entity
     */
    ResponseEntity<O> mapSuccess(O output);

    /**
     * Map a exception object into a response entity
     *
     * @param throwable Exception object
     * @return Response entity
     */
    ResponseEntity<O> mapFailure(Throwable throwable);

}

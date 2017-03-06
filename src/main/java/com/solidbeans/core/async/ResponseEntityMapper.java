package com.solidbeans.core.async;

import org.springframework.http.ResponseEntity;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public interface ResponseEntityMapper<O> {

    ResponseEntity<O> mapSuccess(O output);

    ResponseEntity<O> mapFailure(Throwable throwable);

}

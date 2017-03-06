package com.solidbeans.core.async;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author magnus.wahlstrom@solidbeans.com
 */
public class DefaultResponseEntityMapper<O> implements ResponseEntityMapper<O> {
    @Override
    public ResponseEntity<O> mapSuccess(O output) {
        return new ResponseEntity<>(output, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<O> mapFailure(Throwable throwable) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

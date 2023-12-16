package com.agb.reactiveproducts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductUpdateException extends RuntimeException {

    public ProductUpdateException (String message) {

        super(message);
    }
}

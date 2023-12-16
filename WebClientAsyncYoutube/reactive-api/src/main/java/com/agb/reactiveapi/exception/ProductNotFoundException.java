package com.agb.reactiveapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.OK)
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {

        super(message);
    }
}

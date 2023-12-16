package com.agb.reactiveproducts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class ProductDeleteException extends RuntimeException {

        public ProductDeleteException (String message) {

            super(message);
        }
}

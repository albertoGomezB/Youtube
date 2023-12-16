package com.agb.reactiveapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class GloalControlAdvice {

    private final List<Details> commonErrorDetails = new ArrayList<>();

    public GloalControlAdvice() {

        commonErrorDetails.add(Details.builder()
                .type(TypeErr.ERROR)
                .message("The product ID need to be correctly")
                .build());
    }


    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> productNotFoundException(ProductNotFoundException e) {

        log.error("ProductNotFoundException: {}", e.getMessage());

        return handleException(HttpStatus.NOT_FOUND, e);

    }

    private ResponseEntity<ApiError> handleException (HttpStatus status, Exception ex) {

        ApiError apiError =  ApiError.builder()
                .status(status)
                .date(LocalDateTime.now())
                .message(ex.getMessage())
                .details(commonErrorDetails)
                .build();

        return new ResponseEntity<>(apiError, status);
    }

}

package com.agb.reactiveproducts.exception;

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
public class GlobalControlAdvice {

    private final List<Details> commonErrorDetails = new ArrayList<>();

    public GlobalControlAdvice() {

        commonErrorDetails.add(Details.builder()
                .type(TypeErr.ERROR)
                .message("Ensure the provided product ID is correct and all required fields are provided")
                .build());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> productNotFoundException(ProductNotFoundException e) {

        log.error("ProductNotFoundException: {}", e.getMessage());
        return handleException(HttpStatus.NOT_FOUND, e);
    }

    @ExceptionHandler(ProductBadRequestException.class)
    public ResponseEntity<ApiError> productBadRequestException(ProductBadRequestException e) {

        log.error("ProductBadRequestException: {}", e.getMessage());
        return handleException(HttpStatus.BAD_REQUEST, e);
    }

    @ExceptionHandler(ProductAlreadyExist.class)
    public ResponseEntity<ApiError> productAlreadyExist(ProductAlreadyExist e) {

        log.error("ProductAlreadyExist: {}", e.getMessage());
        return handleException(HttpStatus.CONFLICT, e);
    }

    @ExceptionHandler(ProductDeleteException.class)
    public ResponseEntity<ApiError> productDeleteException(ProductDeleteException e) {

        log.error("ProductDeleteException: {}", e.getMessage());
        return handleException(HttpStatus.NO_CONTENT, e);
    }

    @ExceptionHandler(ProductUpdateException.class)
    public ResponseEntity<ApiError> productUpdateException(ProductUpdateException e) {

        log.error("ProductUpdateException: {}", e.getMessage());
        return handleException(HttpStatus.BAD_REQUEST, e);
    }

    private ResponseEntity<ApiError> handleException(HttpStatus status, Exception ex) {

        ApiError apiError = ApiError.builder()
                .status(status)
                .date(LocalDateTime.now())
                .message(ex.getMessage())
                .path("http://localhost:8020/products")
                .details(commonErrorDetails)
                .build();

        return new ResponseEntity<>(apiError, status);
    }
}

package com.agb.reactiveapi.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiError {

    @NotNull(message = "Status can´t be null")
    private HttpStatus status;

    @NotNull(message = "Date can´t be null")
    @JsonFormat(shape =  JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime date;

    @NotNull(message = "Details can´t be null")
    private List<Details> details;

    @NotBlank(message = "Message can´t be empty")
    private String message;


}

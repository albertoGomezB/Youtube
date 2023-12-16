package com.agb.reactiveapi.exception;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Details {

    private TypeErr type;

    private String message;
}

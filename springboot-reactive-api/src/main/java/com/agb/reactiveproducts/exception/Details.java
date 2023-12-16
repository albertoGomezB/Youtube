package com.agb.reactiveproducts.exception;

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


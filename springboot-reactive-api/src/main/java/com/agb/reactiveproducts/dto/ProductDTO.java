package com.agb.reactiveproducts.dto;

import com.agb.reactiveproducts.model.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO implements Serializable {

    @NotBlank(message = "Code is mandatory")
    private String code;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotNull(message = "Price is mandatory")
    @Min(value = 0, message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Category is mandatory")
    private Category category;
}

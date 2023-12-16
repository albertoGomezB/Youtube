package com.agb.reactiveapi.dto;

import com.agb.reactiveapi.model.Product;

public class MapToDTO {

    private MapToDTO() {
        throw new IllegalArgumentException("Utility class");
    }

    public static ProductDTO mapProductTODTO (Product product) {

        return ProductDTO.builder()
                .id(product.getId())
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    public static Product mapDTOToProduct (ProductDTO productDTO) {

        return Product.builder()
                .id(productDTO.getId())
                .code(productDTO.getCode())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
    }
}

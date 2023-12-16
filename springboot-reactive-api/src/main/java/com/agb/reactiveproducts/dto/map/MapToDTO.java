package com.agb.reactiveproducts.dto.map;

import com.agb.reactiveproducts.dto.ProductDTO;
import com.agb.reactiveproducts.model.Product;

public class MapToDTO {

    private MapToDTO() {
        throw new IllegalStateException("Utility class");
    }

    public static ProductDTO mapProductToDTO (Product product) {

        return ProductDTO.builder()
                .code(product.getCode())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    public static Product mapDTOToProduct(ProductDTO productDTO) {

        return Product.builder()
                .code(productDTO.getCode())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
    }
}

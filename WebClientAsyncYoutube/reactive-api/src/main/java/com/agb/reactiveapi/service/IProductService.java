package com.agb.reactiveapi.service;

import com.agb.reactiveapi.dto.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Flux<ProductDTO> findAllProducts ();

    Flux<ProductDTO> findByPriceBetween(Double min, Double max);

    Mono<ProductDTO> findByCode(String code);

    Mono<ProductDTO> findProductById(String id);

    Mono<ProductDTO> saveProduct (ProductDTO productDTO);

    Mono<ProductDTO> updateProduct (ProductDTO productDTO, String id);

    Mono<Void> deleProduct(String id);

    Mono<Void> deletesAllProducts();
}

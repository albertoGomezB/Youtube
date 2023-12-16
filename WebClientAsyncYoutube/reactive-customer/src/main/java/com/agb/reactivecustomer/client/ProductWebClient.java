package com.agb.reactivecustomer.client;

import com.agb.reactivecustomer.dto.ProductDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductWebClient {

    Flux<ProductDTO> findAllProducts ();

    Flux<ProductDTO> findByPriceBetween(Double min, Double max);

    Mono<ProductDTO> findProductById(String id);

    Mono<ProductDTO> saveProduct (ProductDTO productDTO);

    Mono<Void> deleProduct(String id);

}

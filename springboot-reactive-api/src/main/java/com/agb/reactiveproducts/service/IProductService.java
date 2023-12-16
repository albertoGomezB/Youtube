package com.agb.reactiveproducts.service;

import com.agb.reactiveproducts.dto.ProductDTO;
import org.springframework.data.domain.Pageable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    Flux<ProductDTO> findAllProducts();

    Mono<ProductDTO> findByCode(String code);

    Flux<ProductDTO> findProductsByPriceBetween(double price1, double price2);

    Flux<ProductDTO> findProductsByCategory(String category);

    Mono<ProductDTO> findProductById(String id);

    Mono<ProductDTO> saveProduct(ProductDTO productDTO);

    Mono<ProductDTO> updateProduct(ProductDTO productDTO, String id);

    Mono<Void> deleteProduct(String id);
}

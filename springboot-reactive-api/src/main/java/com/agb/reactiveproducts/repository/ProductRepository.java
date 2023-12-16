package com.agb.reactiveproducts.repository;

import com.agb.reactiveproducts.model.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {

    Mono<Product> findByCode(String code);
    Flux<Product> findByPriceBetween(double price1, double price2);
    Flux<Product> findByCategory(String category);

}

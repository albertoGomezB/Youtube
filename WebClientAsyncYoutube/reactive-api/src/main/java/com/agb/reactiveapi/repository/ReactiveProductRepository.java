package com.agb.reactiveapi.repository;

import com.agb.reactiveapi.model.Product;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface ReactiveProductRepository extends ReactiveMongoRepository<Product, String> {

    Mono<Product> findByCode(String code);

    Flux<Product> findByPriceBetween(Double min, Double max);
}

package com.agb.reactiveapi.controller;

import com.agb.reactiveapi.service.IProductService;
import com.agb.reactiveapi.dto.ProductDTO;
import com.agb.reactiveapi.exception.ProductNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ReactiveProductController {

    private final IProductService iProductService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductDTO> findAllProducts () {

        return iProductService.findAllProducts()
                .switchIfEmpty(Flux.empty());
    }

    @GetMapping("/price/{minPrice}/{maxPrice}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductDTO> findByPriceRange(@PathVariable Double minPrice, @PathVariable Double maxPrice) {

        if (minPrice > maxPrice) {
            return Flux.error(new IllegalArgumentException("Invalid price range. Min price should be less than or equal to max price."));
        }

        return iProductService.findByPriceBetween(minPrice, maxPrice)
                .switchIfEmpty(Flux.empty());
    }


    @GetMapping("/code/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ProductDTO> findByCode (@PathVariable String code) {

        return iProductService.findByCode(code)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("The product is empty")));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ProductDTO> findById (@PathVariable String id) {

        return iProductService.findProductById(id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("The product is empty")));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductDTO> createProduct(@RequestBody @Valid ProductDTO productDTO) {

        return iProductService.saveProduct(productDTO)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("No product found for save")));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ProductDTO> updateProduct(@RequestBody @Valid ProductDTO productDTO, @PathVariable String id) {

        return iProductService.updateProduct(productDTO, id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("No product found for update")));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProductById(@PathVariable String id) {

        return iProductService.deleProduct(id);
    }

    @DeleteMapping("/deleteAll")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteAllProducts() {

        return iProductService.deletesAllProducts();
    }




















}

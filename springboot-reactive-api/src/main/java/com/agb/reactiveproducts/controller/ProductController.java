package com.agb.reactiveproducts.controller;

import com.agb.reactiveproducts.dto.ProductDTO;
import com.agb.reactiveproducts.exception.ProductNotFoundException;
import com.agb.reactiveproducts.service.IProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final IProductService productService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductDTO> findAllProducts () {

        return productService.findAllProducts()
                .switchIfEmpty(Flux.empty());
    }

    @GetMapping("/price")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductDTO> findProductsByPriceBetween (double minPrice, double maxPrice) {

        return productService.findProductsByPriceBetween(minPrice, maxPrice)
                .switchIfEmpty(Flux.error(new ProductNotFoundException
                        ("No products found with price between " + minPrice + " and " + maxPrice)));
    }

    @GetMapping("/category")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductDTO> findProductsByCategory (String category) {

        return productService.findProductsByCategory(category)
                .switchIfEmpty(Flux.error(new ProductNotFoundException
                        ("No products found with category " + category)));
    }

    @GetMapping("/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductDTO> findByCode (@PathVariable String code) {

        return productService.findByCode(code)
                .flatMapMany(Flux::just);  // Mono<ProductDTO> -> Flux<ProductDTO> // convierte un Mono en un Flux
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductDTO> findProductById (@PathVariable String id) {

        return productService.findProductById(id)
                .flatMapMany(Flux::just);  // Mono<ProductDTO> -> Flux<ProductDTO>
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductDTO> createProduct (@RequestBody @Valid ProductDTO productDTO) {

        return productService.saveProduct(productDTO)
                .switchIfEmpty(Mono.error(new ProductNotFoundException
                        ("No product found with code " + productDTO.getCode())));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ProductDTO> updateProduct (@PathVariable String id, @RequestBody @Valid ProductDTO productDTO) {

        return productService.updateProduct(productDTO, id)
                .switchIfEmpty(Mono.error(new ProductNotFoundException
                        ("No product found with code " + productDTO.getCode())));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct (@PathVariable String id) {

        return productService.deleteProduct(id);
    }


}

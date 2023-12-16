package com.agb.reactivecustomer.controller;

import com.agb.reactivecustomer.client.ProductWebClient;
import com.agb.reactivecustomer.dto.ProductDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final ProductWebClient productWebClient;

    public CustomerController(ProductWebClient productWebClient) {
        this.productWebClient = productWebClient;
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public Flux<ProductDTO> findAllProducts () {

        return productWebClient.findAllProducts();
    }

    @GetMapping("/products/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Mono<ProductDTO> findProductById (@PathVariable String id) {

        return productWebClient.findProductById(id);

    }

    @PostMapping("/products")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<ProductDTO> saveProduct(@RequestBody @Valid ProductDTO productDTO) {

        return productWebClient.saveProduct(productDTO);
    }

    @DeleteMapping("/products/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteProduct(@PathVariable String id) {

        return productWebClient.deleProduct(id);
    }
}

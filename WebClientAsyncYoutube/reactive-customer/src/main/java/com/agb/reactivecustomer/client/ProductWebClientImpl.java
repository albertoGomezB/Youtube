package com.agb.reactivecustomer.client;

import com.agb.reactivecustomer.dto.ProductDTO;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductWebClientImpl implements ProductWebClient{

    private static final String BASE_URI = "/products";

    private static final String PRODUCT_ID = "/products/{id}";

    private final WebClient webClient;

    public ProductWebClientImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<ProductDTO> findAllProducts() {

        return webClient
                .get()
                .uri(BASE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ProductDTO.class);
    }

    @Override
    public Flux<ProductDTO> findByPriceBetween(Double min, Double max) {

       /* return webClient
                .get()
                .uri(BASE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ProductDTO.class);*/
        return null;
    }

    @Override
    public Mono<ProductDTO> findProductById(String id) {

        return webClient
                .get()
                .uri(PRODUCT_ID, id)
                .retrieve()
                .bodyToMono(ProductDTO.class);
    }

    @Override
    public Mono<ProductDTO> saveProduct(ProductDTO productDTO) {

        return webClient
                .post()
                .uri(BASE_URI)
                .accept(MediaType.APPLICATION_JSON)
                .bodyValue(productDTO)
                .retrieve()
                .bodyToMono(ProductDTO.class);
    }

    @Override
    public Mono<Void> deleProduct(String id) {

        return webClient
                .delete()
                .uri(PRODUCT_ID, id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Void.class);
    }
}

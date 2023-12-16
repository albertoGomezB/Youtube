package com.agb.reactiveapi.service;

import com.agb.reactiveapi.dto.MapToDTO;
import com.agb.reactiveapi.dto.ProductDTO;
import com.agb.reactiveapi.exception.ProductNotFoundException;
import com.agb.reactiveapi.repository.ReactiveProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Service
@RequiredArgsConstructor
@Transactional
public class IProductServiceImpl implements IProductService {

    private final ReactiveProductRepository productRepository;

    @Override
    public Flux<ProductDTO> findAllProducts() {

        return productRepository.findAll()
                .map(MapToDTO::mapProductTODTO)
/*                .delayElements(Duration.ofSeconds(1))*/
                .switchIfEmpty(Mono.error(new ProductNotFoundException("No found products")));
    }

    @Override
    public Flux<ProductDTO> findByPriceBetween(Double min, Double max) {

        return productRepository.findByPriceBetween(min, max)
                .map(MapToDTO::mapProductTODTO)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Not found products between " + min + " and " + max)));
    }

    @Override
    public Mono<ProductDTO> findByCode(String code) {

        return productRepository.findByCode(code)
                .map(MapToDTO::mapProductTODTO)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Not found the product with code: " + code)));

    }

    @Override
    public Mono<ProductDTO> findProductById(String id) {

        return productRepository.findById(id)
                .map(MapToDTO::mapProductTODTO)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Not found the product with code: " + id)));
    }

    @Override
    public Mono<ProductDTO> saveProduct(ProductDTO productDTO) {

        return productRepository.save(MapToDTO.mapDTOToProduct(productDTO))
                .map(MapToDTO::mapProductTODTO)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("The product for save with code " + productDTO.getCode() + " doesn't exist")));
    }

    @Override
    public Mono<ProductDTO> updateProduct(ProductDTO productDTO, String id) {

        return productRepository.findById(id)
                .flatMap(product -> {
                    product.setCode(productDTO.getCode());
                    product.setName(productDTO.getName());
                    product.setPrice(productDTO.getPrice());
                    return productRepository.save(product);
                })
                .map(MapToDTO::mapProductTODTO)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("Not found the product for update with id " + id)));
    }


    @Override
    public Mono<Void> deleProduct(String id) {

      return  productRepository.findById(id)
                .flatMap(productRepository::delete)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("No found the product for delete with id: " + id)));
    }

    @Override
    public Mono<Void> deletesAllProducts() {

        return productRepository.deleteAll();
    }
}

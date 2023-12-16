package com.agb.reactiveproducts.service;

import com.agb.reactiveproducts.dto.ProductDTO;
import com.agb.reactiveproducts.dto.map.MapToDTO;
import com.agb.reactiveproducts.exception.ProductAlreadyExist;
import com.agb.reactiveproducts.exception.ProductDeleteException;
import com.agb.reactiveproducts.exception.ProductNotFoundException;
import com.agb.reactiveproducts.exception.ProductUpdateException;
import com.agb.reactiveproducts.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Transactional
public class IProductServiceImpl implements IProductService {

    private final ProductRepository productRepository;

    @Override
    public Flux<ProductDTO> findAllProducts() {

        return productRepository.findAll()
                /*.delayElements(java.time.Duration.ofSeconds(1))*/
                .map(MapToDTO::mapProductToDTO)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("No found products")));
    }

    @Override
    public Flux<ProductDTO> findProductsByPriceBetween(double price1, double price2) {

        return productRepository.findByPriceBetween(price1, price2)
                .map(MapToDTO::mapProductToDTO)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("No found products with this price range: " + price1 + " - " + price2)));
    }

    @Override
    public Flux<ProductDTO> findProductsByCategory(String category) {

        return productRepository.findByCategory(category)
                .map(MapToDTO::mapProductToDTO)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("No found products with this category: " + category)));
    }

    @Override
    public Mono<ProductDTO> findByCode(String code) {

        return productRepository.findByCode(code)
                .map(MapToDTO::mapProductToDTO)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("No found the product with code: " + code)));
    }

    @Override
    public Mono<ProductDTO> findProductById(String id) {

        return productRepository.findById(id)
                .map(MapToDTO::mapProductToDTO)
                .switchIfEmpty(Mono.error(new ProductNotFoundException("No found the product with id: " + id)));
    }

    @Override
    public Mono<ProductDTO> saveProduct(ProductDTO productDTO) {

        return productRepository.save(MapToDTO.mapDTOToProduct(productDTO))
                .map(MapToDTO::mapProductToDTO)
                .switchIfEmpty(Mono.error(new ProductAlreadyExist("The product for save with code " + productDTO.getCode() + " already exist")));

    }

    @Override
    public Mono<ProductDTO> updateProduct(ProductDTO productDTO, String id) {

        return productRepository.findById(id)
                .flatMap(product -> {

                    product.setCode(productDTO.getCode());
                    product.setName(productDTO.getName());
                    product.setPrice(productDTO.getPrice());
                    product.setCategory(productDTO.getCategory());
                    return productRepository.save(product);
                })

                .map(MapToDTO::mapProductToDTO)
                .switchIfEmpty(Mono.error(new ProductUpdateException("No found the product for update with id" + id)));

    }

    @Override
    public Mono<Void> deleteProduct(String id) {

        return productRepository.findById(id)
                .flatMap(productRepository::delete)
                .switchIfEmpty(Mono.error(new ProductDeleteException("No found the product for delete with id: " + id)));
    }
}

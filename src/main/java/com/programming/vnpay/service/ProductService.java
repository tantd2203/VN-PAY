package com.programming.vnpay.service;

import com.programming.vnpay.dto.ProductRequest;
import com.programming.vnpay.dto.ProductResponse;
import com.programming.vnpay.entity.Product;
import com.programming.vnpay.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getAllProduct() {

        List<Product> products = productRepository.findAll();

        return products.stream()
                .map(this::mapToProductResponse)
                .toList();
    }

    public void saveProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);

        log.info("Product {} saved successfully", product.getName());
    }

    public void updateProduct(ProductRequest productRequest) {
        Product product = productRepository.findById(productRequest.getId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        productRepository.save(product);

        log.info("Product {} updated successfully", product.getName());
    }

    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        productRepository.delete(product);

        log.info("Product {} deleted successfully", product.getName());
    }

    public ProductRequest getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return ProductRequest.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

}

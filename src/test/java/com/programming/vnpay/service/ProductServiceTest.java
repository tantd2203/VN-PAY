package com.programming.vnpay.service;

import com.programming.vnpay.dto.ProductRequest;
import com.programming.vnpay.dto.ProductResponse;
import com.programming.vnpay.entity.Product;
import com.programming.vnpay.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllProductTest() {
        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);

        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<ProductResponse> products = productService.getAllProduct();

        assertEquals(2, products.size());
        assertEquals(1L, products.get(0).getId());
        assertEquals(2L, products.get(1).getId());
    }

    @Test
    public void saveProductTest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setName("Product 1");
        productRequest.setPrice(100.0);

        Product product = new Product();
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        when(productRepository.save(any(Product.class))).thenReturn(product);

        productService.saveProduct(productRequest);

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void updateProductTest() {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setId(1L);
        productRequest.setName("Updated Product");
        productRequest.setPrice(200.0);

        Product product = new Product();
        product.setId(productRequest.getId());
        product.setName(productRequest.getName());
        product.setPrice(productRequest.getPrice());

        when(productRepository.findById(productRequest.getId())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        productService.updateProduct(productRequest);

        verify(productRepository, times(1)).findById(productRequest.getId());
        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    public void deleteProductTest() {
        Long id = 1L;

        Product product = new Product();
        product.setId(id);

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        productService.deleteProduct(id);

        verify(productRepository, times(1)).findById(id);
        verify(productRepository, times(1)).delete(any(Product.class));
    }
}
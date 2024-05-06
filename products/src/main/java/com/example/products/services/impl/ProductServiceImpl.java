package com.example.products.services.impl;

import com.example.products.exceptions.NotFoundResponseException;
import com.example.products.model.Product;
import com.example.products.repo.ProductRepository;
import com.example.products.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return (List<Product>) productRepository.findAll();
    }

    @Override
    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponseException(id, Product.class));
    }
}

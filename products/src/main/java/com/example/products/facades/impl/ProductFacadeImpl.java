package com.example.products.facades.impl;

import com.example.products.facades.ProductFacade;
import com.example.products.model.Product;
import com.example.products.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductFacadeImpl implements ProductFacade {
    private final ProductService productService;

    public ProductFacadeImpl(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public Product save(Product product) {
        return this.productService.save(product);
    }

    @Override
    public List<Product> getProducts() {
        return this.productService.getProducts();
    }

    @Override
    public Product getProductById(UUID id) {
        return this.productService.getProductById(id);
    }
}

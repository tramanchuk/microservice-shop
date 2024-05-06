package com.example.products.services;

import com.example.products.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product save(Product product);
    List<Product> getProducts();
    Product getProductById(UUID id);
}

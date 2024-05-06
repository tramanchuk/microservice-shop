package com.example.products.facades;

import com.example.products.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductFacade {
    Product save(Product product);
    List<Product> getProducts();
    Product getProductById(UUID id);
}

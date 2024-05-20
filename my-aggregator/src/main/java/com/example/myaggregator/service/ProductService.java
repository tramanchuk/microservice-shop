package com.example.myaggregator.service;

import com.example.myaggregator.model.products.Product;
import reactor.core.publisher.Mono;
import java.util.List;


public interface ProductService {
    Mono<Product> getProductById(String id);
    Mono<List<Product>> getProducts();
    Mono<List<Product>> getProductsFailed();
}

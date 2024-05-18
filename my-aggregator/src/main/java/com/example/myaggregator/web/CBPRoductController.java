package com.example.myaggregator.web;

import com.example.myaggregator.model.products.Product;
import com.example.myaggregator.service.AggregatorProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@RequestMapping("/v1/aggregator/products")
public class CBPRoductController {
    private final AggregatorProductService productService;

    public CBPRoductController(AggregatorProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/success")
    public Mono<List<Product>> getProductsSuccess() {
        return this.productService.getProducts();
    }
    @GetMapping("/failure")
    public Mono<List<Product>> getProductsFailed() {
        return this.productService.getProductsFailed();
    }
}

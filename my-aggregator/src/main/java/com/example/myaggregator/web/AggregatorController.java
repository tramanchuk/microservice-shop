package com.example.myaggregator.web;

import com.example.myaggregator.model.AggregatedOrder;
import com.example.myaggregator.model.products.Product;
import com.example.myaggregator.service.AggregatorService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/aggregate/orders")
public class AggregatorController {
    private final AggregatorService aggregatorService;

    public AggregatorController(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
    }

    @GetMapping("/{id}")
    public Mono<Product> aggregateOrder(@PathVariable String id) {
        return aggregatorService.getFullOrderInformation(id);
    }
    @GetMapping
    public Mono<AggregatedOrder> aggregateOrders() {
        return null;
    }
}

package com.example.myaggregator.web;

import com.example.myaggregator.model.AggregatedOrder;
import com.example.myaggregator.model.customers.Customer;
import com.example.myaggregator.model.orders.Order;
import com.example.myaggregator.service.OrderAggregatorService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/aggregate/orders")
public class AggregatorController {
    private final OrderAggregatorService orderAggregatorService;

    public AggregatorController(OrderAggregatorService orderAggregatorService) {
        this.orderAggregatorService = orderAggregatorService;
    }

    @GetMapping("/{id}")
    public AggregatedOrder aggregateOrder(@PathVariable String id) {
        return orderAggregatorService.getFullOrderInformation(id);
    }
    @GetMapping
    public Mono<AggregatedOrder> aggregateOrders() {
        return null;
    }
}

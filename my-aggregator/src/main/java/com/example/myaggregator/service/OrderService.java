package com.example.myaggregator.service;

import com.example.myaggregator.model.orders.Order;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<Order> getOrderById(String id);
}

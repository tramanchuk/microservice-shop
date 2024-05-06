package com.example.orders.services;

import com.example.orders.model.Order;

import java.util.List;
import java.util.UUID;

public interface OrderService {
    Order save(Order order);
    List<Order> getOrders();
    List<Order> getOrders(String customerId);
    Order getOrderById(UUID id);
}

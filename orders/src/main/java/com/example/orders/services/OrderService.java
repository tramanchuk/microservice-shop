package com.example.orders.services;

import com.example.orders.model.Order;

import java.util.List;

public interface OrderService {
    Order save(Order order);
    List<Order> getOrders();
    List<Order> getOrders(Long customerId);
    Order getOrderById(Long id);
}

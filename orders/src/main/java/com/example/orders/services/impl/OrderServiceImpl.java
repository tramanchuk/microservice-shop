package com.example.orders.services.impl;

import com.example.orders.exceptions.NotFoundResponseException;
import com.example.orders.model.Order;
import com.example.orders.repo.OrderRepository;
import com.example.orders.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getOrders() {
        return (List<Order>)orderRepository.findAll();
    }

    @Override
    public List<Order> getOrders(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponseException(id, Order.class));
    }
}

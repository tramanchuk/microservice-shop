package com.example.orders.facades.impl;

import com.example.orders.facades.OrderFacade;
import com.example.orders.model.Order;
import com.example.orders.model.OrderLine;
import com.example.orders.repo.OrderLineRepository;
import com.example.orders.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderFacadeImpl implements OrderFacade {
    private final OrderService orderService;
    private final OrderLineRepository orderLineRepository;

    public OrderFacadeImpl(OrderService orderService, OrderLineRepository orderLineRepository) {
        this.orderService = orderService;
        this.orderLineRepository = orderLineRepository;
    }

    @Override
    public Order save(Order order) {
        return this.orderService.save(order);
    }

    @Override
    public List<Order> getOrders() {
        return this.orderService.getOrders();
    }

    @Override
    public List<Order> getOrders(Long customerId) {
        return this.orderService.getOrders(customerId);
    }

    @Override
    public Order getOrderById(Long id) {
        return this.orderService.getOrderById(id);
    }

    @Override
    public OrderLine addOrderLine(Long orderId, OrderLine line) {
        Order order = this.orderService.getOrderById(orderId);
        return addLine(order, line);
    }

    private OrderLine addLine(Order order, OrderLine line){
        line = orderLineRepository.save(line);
        List<OrderLine> lines = order.getLines();
        lines.add(line);
        this.orderService.save(order);
        return line;
    }
}

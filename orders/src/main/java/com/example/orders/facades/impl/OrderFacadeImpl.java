package com.example.orders.facades.impl;

import com.example.orders.facades.OrderFacade;
import com.example.orders.kafka.OrderEvent;
import com.example.orders.kafka.OrderEventProducer;
import com.example.orders.model.Order;
import com.example.orders.model.OrderLine;
import com.example.orders.repo.OrderLineRepository;
import com.example.orders.services.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderFacadeImpl implements OrderFacade {
    private final OrderService orderService;
    private final OrderLineRepository orderLineRepository;
    private final OrderEventProducer orderEventProducer;

    public OrderFacadeImpl(OrderService orderService, OrderLineRepository orderLineRepository, OrderEventProducer orderEventProducer) {
        this.orderService = orderService;
        this.orderLineRepository = orderLineRepository;
        this.orderEventProducer = orderEventProducer;
    }

    @Override
    public Order save(Order order) {
        order = this.orderService.save(order);
        this.orderEventProducer.sendOrderEvent(new OrderEvent(order.getId()));
        return order;
    }

    @Override
    public List<Order> getOrders() {
        return this.orderService.getOrders();
    }

    @Override
    public List<Order> getOrders(String customerId) {
        return this.orderService.getOrders(customerId);
    }

    @Override
    public Order getOrderById(UUID id) {
        return this.orderService.getOrderById(id);
    }

    @Override
    public OrderLine addOrderLine(UUID orderId, OrderLine line) {
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

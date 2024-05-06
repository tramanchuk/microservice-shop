package com.example.orders.facades;

import com.example.orders.model.Order;
import com.example.orders.model.OrderLine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface OrderFacade {
    Order save(Order order);
    List<Order> getOrders();
    List<Order> getOrders(String customerId);
    Order getOrderById(UUID id);
    OrderLine addOrderLine(UUID orderId, OrderLine line);
}

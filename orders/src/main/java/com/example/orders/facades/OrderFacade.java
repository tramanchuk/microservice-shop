package com.example.orders.facades;

import com.example.orders.model.Order;
import com.example.orders.model.OrderLine;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderFacade {
    Order save(Order order);
    List<Order> getOrders();
    List<Order> getOrders(Long customerId);
    Order getOrderById(Long id);
    OrderLine addOrderLine(Long orderId, OrderLine line);
}

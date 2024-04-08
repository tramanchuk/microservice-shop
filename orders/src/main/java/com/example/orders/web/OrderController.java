package com.example.orders.web;

import com.example.orders.model.Order;
import com.example.orders.model.OrderLine;
import com.example.orders.repo.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    final private OrderRepository orderRepository;

    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> getAllOrders(){
        return (List<Order>) this.orderRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestBody Order order){
        this.orderRepository.save(order);
    }

    @PostMapping("/{orderId}/orderLine")
    public void addOrderLine(@RequestBody OrderLine line, @PathVariable Long orderId){
        Optional<Order> order = this.orderRepository.findById(orderId);
        addLine(order.orElseThrow(() -> throw404(orderId)), line);
    }

    private static NoSuchElementException throw404(Long orderId) {
        return new NoSuchElementException("Order with id=" + orderId + " not found");
    }

    private void addLine(Order order, OrderLine line){
        List<OrderLine> lines = order.getLines();
        lines.add(line);
        order.setLines(lines);
        this.orderRepository.save(order);
    }
}

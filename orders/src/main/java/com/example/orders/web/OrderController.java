package com.example.orders.web;

import com.example.orders.config.logs.Loggable;
import com.example.orders.converters.OrderConverter;
import com.example.orders.facades.OrderFacade;
import com.example.orders.model.Order;
import com.example.orders.web.dto.OrderFullDto;
import com.example.orders.web.dto.OrderShortDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import io.vavr.control.Try;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {
    private final OrderFacade orderFacade;
    private final OrderConverter orderConverter;

    public OrderController(OrderFacade orderFacade, OrderConverter orderConverter) {
        this.orderFacade = orderFacade;
        this.orderConverter = orderConverter;
    }

    @Loggable
    @GetMapping("/{orderId}")
    public OrderFullDto getOrder(@PathVariable UUID orderId){
        if (UUID.fromString("570ed148-64e2-480e-9212-1af6a910490e").equals(orderId)){
            Try.run(() -> Thread.sleep(5000));
        }
        Order order = this.orderFacade.getOrderById(orderId);
        return this.orderConverter.convert(order);
    }
    @Loggable
    @GetMapping
    public List<OrderShortDto> getAllOrders(){
        List<Order> orders = this.orderFacade.getOrders();
        return this.orderConverter.convert(orders);
    }
    @Loggable
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderFullDto createOrder(@RequestBody OrderFullDto orderDto){
        Order order = this.orderConverter.convert(orderDto);
        this.orderFacade.save(order);
        return this.orderConverter.convert(order);
    }
}

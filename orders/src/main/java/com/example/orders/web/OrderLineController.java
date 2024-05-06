package com.example.orders.web;

import com.example.orders.config.logs.Loggable;
import com.example.orders.converters.OrderLineConverter;
import com.example.orders.facades.OrderFacade;
import com.example.orders.model.OrderLine;
import com.example.orders.web.dto.OrderLineDto;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/orders/{orderId}/orderLines")
public class OrderLineController {
    final private OrderFacade orderFacade;
    private final OrderLineConverter orderLineConverter;

    public OrderLineController(OrderFacade orderFacade, OrderLineConverter orderLineConverter) {
        this.orderFacade = orderFacade;
        this.orderLineConverter = orderLineConverter;
    }
    @Loggable
    @PostMapping
    public OrderLineDto addOrderLine(@PathVariable UUID orderId, @RequestBody OrderLineDto lineDto){
        OrderLine line = this.orderLineConverter.convert(lineDto);
        line = this.orderFacade.addOrderLine(orderId, line);
        return this.orderLineConverter.convert(line);
    }
}

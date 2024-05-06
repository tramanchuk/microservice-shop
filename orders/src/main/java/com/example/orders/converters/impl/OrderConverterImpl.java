package com.example.orders.converters.impl;

import com.example.orders.converters.OrderConverter;
import com.example.orders.converters.OrderLineConverter;
import com.example.orders.model.Order;
import com.example.orders.web.dto.OrderFullDto;
import com.example.orders.web.dto.OrderShortDto;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderConverterImpl implements OrderConverter {
    private final OrderLineConverter orderLineConverter;

    public OrderConverterImpl(OrderLineConverter orderLineConverter) {
        this.orderLineConverter = orderLineConverter;
    }

    @Override
    public OrderFullDto convert(Order order) {
        return new OrderFullDto(order.getId(),
                order.getCustomerId(),
                this.orderLineConverter.convertToDto(order.getLines()),
                order.getCreatedDate());
    }

    @Override
    public OrderShortDto convertToShort(Order order) {
        return new OrderShortDto(order.getId(),
                order.getCustomerId(),
                order.getCreatedDate());
    }

    @Override
    public List<OrderShortDto> convert(List<Order> orders) {
        return orders.stream().map(this::convertToShort)
                .collect(Collectors.toList());
    }

    @Override
    public Order convert(OrderFullDto orderDto) {
        return new Order(orderDto.getId(), orderDto.getCustomerId(),
                this.orderLineConverter.convertFromDto(orderDto.getLines()));
    }
}

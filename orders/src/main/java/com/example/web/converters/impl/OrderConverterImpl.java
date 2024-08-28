package com.example.web.converters.impl;

import com.example.web.converters.OrderConverter;
import com.example.web.converters.OrderLineConverter;
import com.example.orders.model.Order;
import com.example.web.dto.OrderFullDto;
import com.example.web.dto.OrderShortDto;
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
        return OrderFullDto.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .lines(this.orderLineConverter.convertToDto(order.getLines()))
                .createdDate(order.getCreatedDate())
                .discountPrice(order.getTotalDiscount())
                .subtotalPrice(order.getSubtotalPrice())
                .deliveryPrice(order.getDeliveryPrice())
                .totalPrice(order.getTotalPrice())
                .build();
    }

    @Override
    public OrderShortDto convertToShort(Order order) {
        return OrderShortDto.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .totalPrice(order.getTotalPrice())
                .createdDate(order.getCreatedDate())
                .build();
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

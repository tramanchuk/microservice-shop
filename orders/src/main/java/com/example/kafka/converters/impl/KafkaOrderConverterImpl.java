package com.example.kafka.converters.impl;

import com.example.kafka.converters.OrderConverter;
import com.example.kafka.converters.OrderLineConverter;
import com.example.kafka.dto.OrderDto;
import com.example.orders.model.Order;
import org.springframework.stereotype.Service;

@Service
public class KafkaOrderConverterImpl implements OrderConverter {

    private final OrderLineConverter orderLineConverter;

    public KafkaOrderConverterImpl(OrderLineConverter orderLineConverter) {
        this.orderLineConverter = orderLineConverter;
    }

    @Override
    public OrderDto convert(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .lines(this.orderLineConverter.convert(order.getLines()))
                .subtotalPrice(order.getSubtotalPrice())
                .deliveryPrice(order.getDeliveryPrice())
                .discountPrice(order.getTotalDiscount())
                .totalPrice(order.getTotalPrice())
                .build();
    }
}

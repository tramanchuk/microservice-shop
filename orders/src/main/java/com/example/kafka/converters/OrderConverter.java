package com.example.kafka.converters;

import com.example.kafka.dto.OrderDto;
import com.example.orders.model.Order;

public interface OrderConverter {
    OrderDto convert(Order order);
}

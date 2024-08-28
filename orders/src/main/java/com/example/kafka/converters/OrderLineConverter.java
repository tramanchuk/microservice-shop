package com.example.kafka.converters;

import com.example.kafka.dto.OrderLineDto;
import com.example.orders.model.OrderLine;

import java.util.List;

public interface OrderLineConverter {
    OrderLineDto convert(OrderLine line);
    List<OrderLineDto> convert(List<OrderLine> lines);
}

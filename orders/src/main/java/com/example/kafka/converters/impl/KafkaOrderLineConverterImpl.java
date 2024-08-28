package com.example.kafka.converters.impl;

import com.example.kafka.converters.OrderLineConverter;
import com.example.kafka.dto.OrderLineDto;
import com.example.orders.model.OrderLine;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KafkaOrderLineConverterImpl implements OrderLineConverter {
    @Override
    public OrderLineDto convert(OrderLine line) {
        return OrderLineDto.builder()
                .id(line.getId())
                .productId(line.getProductId())
                .quantity(line.getQuantity())
                .unitPrice(line.getUnitPrice())
                .discountPrice(line.getDiscountPrice())
                .build();
    }

    @Override
    public List<OrderLineDto> convert(List<OrderLine> lines) {
        return lines.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}

package com.example.web.converters.impl;

import com.example.web.converters.OrderLineConverter;
import com.example.orders.model.OrderLine;
import com.example.web.dto.OrderLineDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderLineConverteImpl implements OrderLineConverter {
    @Override
    public OrderLineDto convert(OrderLine line) {
        return OrderLineDto.builder()
                .id(line.getId())
                .productId(line.getProductId())
                .quantity(line.getQuantity())
                .discountPrice(line.getDiscountPrice())
                .unitPrice(line.getUnitPrice())
                .build();
    }

    @Override
    public List<OrderLineDto> convertToDto(List<OrderLine> lines) {
        return lines.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public OrderLine convert(OrderLineDto lineDto) {
        return new OrderLine(lineDto.getId(), lineDto.getProductId(), lineDto.getQuantity());
    }

    @Override
    public List<OrderLine> convertFromDto(List<OrderLineDto> linesDto) {
        return linesDto.stream().map(this::convert).collect(Collectors.toList());
    }
}

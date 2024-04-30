package com.example.orders.converters.impl;

import com.example.orders.converters.OrderLineConverter;
import com.example.orders.model.OrderLine;
import com.example.orders.web.dto.OrderLineDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderLineConverteImpl implements OrderLineConverter {
    @Override
    public OrderLineDto convert(OrderLine line) {
        return new OrderLineDto(line.getId(), line.getProductId(), line.getQuantity());
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

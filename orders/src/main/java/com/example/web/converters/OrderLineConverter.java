package com.example.web.converters;

import com.example.orders.model.OrderLine;
import com.example.web.dto.OrderLineDto;
import java.util.List;

public interface OrderLineConverter {
    OrderLineDto convert(OrderLine line);
    List<OrderLineDto> convertToDto(List<OrderLine> lines);
    OrderLine convert(OrderLineDto lineDto);
    List<OrderLine> convertFromDto(List<OrderLineDto> linesDto);
}

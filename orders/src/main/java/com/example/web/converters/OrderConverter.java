package com.example.web.converters;

import com.example.orders.model.Order;
import com.example.web.dto.OrderFullDto;
import com.example.web.dto.OrderShortDto;
import java.util.List;

public interface OrderConverter {
    OrderFullDto convert(Order order);
    OrderShortDto convertToShort(Order order);
    List<OrderShortDto> convert(List<Order> orders);
    Order convert(OrderFullDto orderDto);
}

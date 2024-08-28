package com.example.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
    private UUID id;
    private String customerId;
    private List<OrderLineDto> lines;
    Double subtotalPrice;
    Double deliveryPrice;
    Double discountPrice;
    Double totalPrice;
}

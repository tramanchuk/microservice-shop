package com.example.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineDto {
    private UUID id;
    String productId;
    Integer quantity;
    Double unitPrice;
    Double discountPrice;
}

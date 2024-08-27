package com.example.orders.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class OrderEvent {
    private UUID id;
}

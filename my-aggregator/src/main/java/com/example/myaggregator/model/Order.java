package com.example.myaggregator.model;

import lombok.*;

import java.util.Objects;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    Long id;
    String customerId;
    List<OrderLine> lines;
}


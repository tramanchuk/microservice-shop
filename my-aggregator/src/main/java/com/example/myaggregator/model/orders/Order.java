package com.example.myaggregator.model.orders;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    String id;
    String customerId;
    List<OrderLine> lines;
}


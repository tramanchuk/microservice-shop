package com.example.myaggregator.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class FullOrderData {
    private Order order;
    private Customer customer;
}

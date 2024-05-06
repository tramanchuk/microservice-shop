package com.example.myaggregator.model;

import com.example.myaggregator.model.customers.Customer;
import com.example.myaggregator.model.orders.Order;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AggregatedOrder {
    private Order order;
    private Customer customer;
}

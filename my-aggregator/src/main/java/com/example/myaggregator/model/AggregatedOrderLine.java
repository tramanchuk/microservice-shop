package com.example.myaggregator.model;

import com.example.myaggregator.model.products.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AggregatedOrderLine {
    String id;
    Product product;
    Integer quantity;
}


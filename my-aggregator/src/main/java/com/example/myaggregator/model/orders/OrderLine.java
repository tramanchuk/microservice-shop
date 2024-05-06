package com.example.myaggregator.model.orders;

import com.example.myaggregator.model.products.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {
    String id;
    String productId;
    Integer quantity;
    Product product;
}


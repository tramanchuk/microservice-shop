package com.example.myaggregator.model.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    String id;
    String firstName;
    String secondName;
}


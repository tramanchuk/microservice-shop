package com.example.myaggregator.service;

import com.example.myaggregator.model.customers.Customer;
import reactor.core.publisher.Mono;

public interface CustomerService {
    Mono<Customer> getCustomerById(String id);
}

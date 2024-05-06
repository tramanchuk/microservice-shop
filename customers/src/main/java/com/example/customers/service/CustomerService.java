package com.example.customers.service;

import com.example.customers.model.Customer;
import java.util.List;
import java.util.UUID;

public interface CustomerService {
    Customer save(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(UUID id);
}

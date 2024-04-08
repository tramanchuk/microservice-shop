package com.example.customers.service;

import com.example.customers.model.Customer;
import java.util.List;

public interface CustomerService {
    Customer save(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
}

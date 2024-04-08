package com.example.customers.service.impl;

import com.example.customers.exceptions.NotFoundResponseException;
import com.example.customers.model.Customer;
import com.example.customers.repo.CustomerRepository;
import com.example.customers.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    final private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer save(Customer customer) {
        return this.customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return (List<Customer>) this.customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundResponseException(id + "", Customer.class));
    }
}

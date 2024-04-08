package com.example.customers.repo;

import org.springframework.data.repository.CrudRepository;
import com.example.customers.model.Customer;
public interface CustomerRepository extends CrudRepository<Customer, Long> {
}

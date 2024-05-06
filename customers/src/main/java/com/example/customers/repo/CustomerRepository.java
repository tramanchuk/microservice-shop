package com.example.customers.repo;

import org.springframework.data.repository.CrudRepository;
import com.example.customers.model.Customer;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, UUID> {
}

package com.example.customers.web;

import com.example.customers.model.Customer;
import com.example.customers.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import io.vavr.control.Try;

@RestController
@RequestMapping("/v1/customers")
public class CustomerController {

    final private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers(){
        return this.customerService.getAllCustomers();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer){
        return this.customerService.save(customer);
    }

    @GetMapping("/{customerId}")
    public Customer getCustomerById(@PathVariable UUID customerId){
        if (UUID.fromString("570ed148-64e2-480e-9212-1af6a910490e").equals(customerId)){
            Try.run(() -> Thread.sleep(5000));
        }
        return this.customerService.getCustomerById(customerId);
    }

}

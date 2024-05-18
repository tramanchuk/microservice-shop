package com.example.myaggregator.service;

import com.example.myaggregator.model.customers.Customer;
import com.example.myaggregator.model.products.Product;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.retry.RetryOperator;
import io.github.resilience4j.reactor.timelimiter.TimeLimiterOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {
    @Autowired
    private WebClient.Builder webClientBuilder;
    private final CircuitBreaker remittanceServiceCircuitBreaker;
    private final Retry remittanceServiceRetry;
    private final TimeLimiter remittanceServiceTimeLimiter;

    public CustomerService(CircuitBreaker remittanceServiceCircuitBreaker, Retry remittanceServiceRetry, TimeLimiter remittanceServiceTimeLimiter) {
        this.remittanceServiceCircuitBreaker = remittanceServiceCircuitBreaker;
        this.remittanceServiceRetry = remittanceServiceRetry;
        this.remittanceServiceTimeLimiter = remittanceServiceTimeLimiter;
    }
    public Mono<Customer> getCustomerById(String id){
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/v1/customers/" + id)
                .retrieve()
                .bodyToMono(Customer.class)
                .transformDeferred(CircuitBreakerOperator.of(remittanceServiceCircuitBreaker))
                .transformDeferred(RetryOperator.of(remittanceServiceRetry))
                .transformDeferred(TimeLimiterOperator.of(remittanceServiceTimeLimiter));
    }
}

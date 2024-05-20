package com.example.myaggregator.service.impl;

import com.example.myaggregator.config.urls.UrlDiscoveryEureka;
import com.example.myaggregator.model.customers.Customer;
import com.example.myaggregator.service.CustomerService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.reactor.circuitbreaker.operator.CircuitBreakerOperator;
import io.github.resilience4j.reactor.retry.RetryOperator;
import io.github.resilience4j.reactor.timelimiter.TimeLimiterOperator;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final String SERVICE_NAME = "CUSTOMER-SERVICE";
    private static final String BASE_PATH = "/v1/customers";
    @Autowired
    private WebClient.Builder webClientBuilder;
    private final UrlDiscoveryEureka urlDiscoveryUtil;
    private final CircuitBreaker remittanceServiceCircuitBreaker;
    private final Retry remittanceServiceRetry;
    private final TimeLimiter remittanceServiceTimeLimiter;

    public CustomerServiceImpl(UrlDiscoveryEureka urlDiscoveryUtil, CircuitBreaker remittanceServiceCircuitBreaker, Retry remittanceServiceRetry, TimeLimiter remittanceServiceTimeLimiter) {
        this.urlDiscoveryUtil = urlDiscoveryUtil;
        this.remittanceServiceCircuitBreaker = remittanceServiceCircuitBreaker;
        this.remittanceServiceRetry = remittanceServiceRetry;
        this.remittanceServiceTimeLimiter = remittanceServiceTimeLimiter;
    }

    public Mono<Customer> getCustomerById(String id){
        return webClientBuilder.build()
                .get()
                .uri(getBaseUrl() + "/" + id)
                .retrieve()
                .bodyToMono(Customer.class)
                .transformDeferred(CircuitBreakerOperator.of(remittanceServiceCircuitBreaker))
                .transformDeferred(RetryOperator.of(remittanceServiceRetry))
                .transformDeferred(TimeLimiterOperator.of(remittanceServiceTimeLimiter));
    }
    public String getBaseUrl() {
        return this.urlDiscoveryUtil.getServerUrl(SERVICE_NAME) + BASE_PATH;
    }
}

package com.example.myaggregator.service.impl;

import com.example.myaggregator.config.urls.UrlDiscoveryEureka;
import com.example.myaggregator.model.products.Product;
import com.example.myaggregator.service.ProductService;
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
public class ProductServiceImpl implements ProductService {
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private UrlDiscoveryEureka urlDiscoveryUtil;
    private final CircuitBreaker remittanceServiceCircuitBreaker;
    private final Retry remittanceServiceRetry;
    private final TimeLimiter remittanceServiceTimeLimiter;
    private static final String SERVICE_NAME = "PRODUCT-SERVICE";
    private static final String BASE_PATH = "/v1/products";

    public ProductServiceImpl(CircuitBreaker remittanceServiceCircuitBreaker, Retry remittanceServiceRetry, TimeLimiter remittanceServiceTimeLimiter) {
        this.remittanceServiceCircuitBreaker = remittanceServiceCircuitBreaker;
        this.remittanceServiceRetry = remittanceServiceRetry;
        this.remittanceServiceTimeLimiter = remittanceServiceTimeLimiter;
    }
    public Mono<Product> getProductById(String id){
        return webClientBuilder.build()
                .get()
                .uri(getBaseUrl() + "/" + id)
                .retrieve()
                .bodyToMono(Product.class)
                .transformDeferred(CircuitBreakerOperator.of(remittanceServiceCircuitBreaker))
                .transformDeferred(RetryOperator.of(remittanceServiceRetry))
                .transformDeferred(TimeLimiterOperator.of(remittanceServiceTimeLimiter));
    }
    public Mono<List<Product>> getProducts(){
        return webClientBuilder.build()
                .get()
                .uri(getBaseUrl())
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                .transformDeferred(CircuitBreakerOperator.of(remittanceServiceCircuitBreaker))
                .transformDeferred(RetryOperator.of(remittanceServiceRetry))
                .transformDeferred(TimeLimiterOperator.of(remittanceServiceTimeLimiter))
                .onErrorReturn(new ArrayList<Product>());
    }
    public Mono<List<Product>> getProductsFailed(){
        return webClientBuilder.build()
                .get()
                .uri(getBaseUrl() + "/failure")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {})
                .transformDeferred(CircuitBreakerOperator.of(remittanceServiceCircuitBreaker))
                .transformDeferred(RetryOperator.of(remittanceServiceRetry))
                .transformDeferred(TimeLimiterOperator.of(remittanceServiceTimeLimiter));
        //.onErrorReturn(new ArrayList<Product>());
    }
    public String getBaseUrl() {
        return this.urlDiscoveryUtil.getServerUrl(SERVICE_NAME) + BASE_PATH;
    }
}

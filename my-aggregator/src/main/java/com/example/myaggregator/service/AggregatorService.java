package com.example.myaggregator.service;

import com.example.myaggregator.model.AggregatedData;
import com.example.myaggregator.model.Customer;
import com.example.myaggregator.model.Order;
import com.example.myaggregator.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class AggregatorService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public AggregatedData getAggregatedData(Long orderId) {
        Order order = webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/v1/orders/" + orderId)
                .retrieve()
                .bodyToMono(Order.class)
                .block();

        Customer customer = webClientBuilder.build()
                .get()
                .uri("http://localhost:8081/v1/customers/" + order.getCustomerId())
                .retrieve()
                .bodyToMono(Customer.class)
                .block();

        Product product = webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/v1/products" + )
        return new AggregatedData(customer, product, order);
    }
}

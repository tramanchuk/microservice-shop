package com.example.myaggregator.service;

import com.example.myaggregator.exceptions.NotFoundResponseException;
import com.example.myaggregator.model.*;
import com.example.myaggregator.model.customers.Customer;
import com.example.myaggregator.model.orders.Order;
import com.example.myaggregator.model.products.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class AggregatorService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    //@Cacheable(value = "aggregatedData", key = "#customerId")
    public AggregatedOrder getFullOrderInformation(String orderId) {
        Order order = getOrder(orderId);
        Mono<Customer> customer = getCustomer(order.getCustomerId());
        if (order.getLines().size() <5) {
            //for orders with a few items
            order.getLines().stream().parallel().forEach(line ->
                    line.setProduct(getProduct(line.getProductId()))
            );
        }else{
            //for orders with many lines, combine them in one call to products service
        }
        customer.block();
        return new AggregatedOrder(order, customer.block());
    }
    public Order getOrder(String orderId){
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8083/v1/orders/" + orderId)
                .retrieve().onStatus(httpStatus -> httpStatus.value() == 404,
                        error -> Mono.error(new NotFoundResponseException(orderId, Order.class)))
                .bodyToMono(Order.class).block();
    }
    public Mono<Customer> getCustomer(String customerId){
        return webClientBuilder
                .build()
                .get()
                .uri("http://localhost:8081/v1/customers/" + customerId)
                .retrieve()
                .onStatus(httpStatus -> httpStatus.value() == 404,
                        error -> Mono.error(new NotFoundResponseException(customerId, Customer.class)))
                .bodyToMono(Customer.class);
    }
    public Product getProduct(String productId){
        return webClientBuilder.build()
                .get()
                .uri("http://localhost:8082/v1/products/" + productId)
                .retrieve().onStatus(httpStatus -> httpStatus.value() == 404,
                        error -> Mono.error(new NotFoundResponseException(productId, Product.class)))
                .bodyToMono(Product.class).block();
    }

    //        Set<Long> orderProductsId = order.getLines().stream().map(OrderLine::getProductId).collect(Collectors.toSet());
//        ProductFilter filter = new ProductFilter(orderProductsId);
//
//        Mono<List<Product>> products = webClientBuilder.build()
//                .post()
//                .uri("http://localhost:8082/v1/products/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(filter)
//                .retrieve()
//                .bodyToMono(new ParameterizedTypeReference<List<Product>>() {});
//        return Mono.zip(customer, products)
//                .map(tuple -> {
////                    order.getLines().forEach(line -> {
////                        products.filter(product -> product.getId())
////                    });
//                    return new FullOrderData(order, tuple.getT1());
//                }).block();

    //@Cacheable(value = "aggregatedData", key = "#customerId")
//    public AggregatedData getOrders(Long customerId) {
//
//        Mono<Customer> customer = webClientBuilder.build()
//                .get()
//                .uri("http://localhost:8081/v1/customers/" + customerId)
//                .retrieve()
//                .bodyToMono(Customer.class);
//
//        Order orders = webClientBuilder.build()
//                .post()
//                .uri("http://localhost:8083/v1/orders")
//                .bodyValue().attribute("customerId", customerId)
//                .retrieve()
//                .bodyToMono(Order.class)
//                .block();
//
//        Product product = webClientBuilder.build()
//                .get()
//                .uri("http://localhost:8082/v1/products" + )
//        Mono.zip(
//                webClientBuilder.build().get().uri("http://product-service/products").retrieve().bodyToMono(Product[].class),
//                webClientBuilder.build().get().uri("http://order-service/orders").retrieve().bodyToMono(Order[].class)
//        ).flatMap(tuple2 -> {
//            // Aggregate the data and create the response object
//            return null;
//        });
//        Mono<Product> product1 = webClientBuilder.build().get().uri("http://product-service/products/{id}", productId).retrieve().bodyToMono(Product.class);
//        Mono<Customer> customer1 = webClientBuilder.build().get().uri("http://customer-service/customers/{id}", customerId).retrieve().bodyToMono(Customer.class);
//
//        return Mono.zip(product, customer)
//                .map(tuple -> {
//                    // Create aggregated object here
//                });
//        return new AggregatedData(customer, product, order);
//    }
}

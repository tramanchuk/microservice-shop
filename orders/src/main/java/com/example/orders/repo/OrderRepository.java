package com.example.orders.repo;

import com.example.orders.model.Order;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByCustomerId(Long customerId);
}

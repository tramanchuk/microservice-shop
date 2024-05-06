package com.example.orders.repo;

import com.example.orders.model.OrderLine;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderLineRepository extends CrudRepository<OrderLine, UUID> {
}

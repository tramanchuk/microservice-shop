package com.example.orders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="my_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column
    Long customerId;

    @OneToMany(cascade = CascadeType.ALL)
    List<OrderLine> lines;

    @Column
    Date createdDate;

    @Column
    Date lastUpdateDate;

    public Order(Long id, Long customerId, List<OrderLine> lines) {
        this.id = id;
        this.customerId = customerId;
        this.lines = lines;
    }
}

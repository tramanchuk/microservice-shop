package com.example.orders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column
    Long customerId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    List<OrderLine> lines;

    @CreationTimestamp
    Date createdDate;

    @UpdateTimestamp
    Date lastUpdateDate;

    public Order(UUID id, Long customerId, List<OrderLine> lines) {
        this.id = id;
        this.customerId = customerId;
        this.lines = lines;
    }
}

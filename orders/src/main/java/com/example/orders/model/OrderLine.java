package com.example.orders.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="order_lines")
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    @Column
    String productId;
    @Column
    Integer quantity;
    @Column
    Double unitPrice;
    @Column
    Double discountPrice;
    @CreationTimestamp
    Date createdDate;
    @UpdateTimestamp
    Date lastUpdateDate;

    public OrderLine(UUID id, String productId, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.unitPrice = 10.0;
        this.discountPrice = 1.0;
    }
}

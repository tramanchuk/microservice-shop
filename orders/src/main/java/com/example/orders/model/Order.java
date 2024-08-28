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
    String customerId;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    List<OrderLine> lines;

    @Column
    Double deliveryPrice;
    @Column
    Double orderDiscountPrice;

    @CreationTimestamp
    Date createdDate;

    @UpdateTimestamp
    Date lastUpdateDate;

    public Order(UUID id, String customerId, List<OrderLine> lines) {
        this.id = id;
        this.customerId = customerId;
        this.lines = lines;
        this.deliveryPrice = 2.2;
        this.orderDiscountPrice = 1.1;
    }
    public Double getSubtotalPrice(){
        return this.lines.stream()
                .mapToDouble(line -> line.getUnitPrice() * line.getQuantity())
                .sum();
    }
    public Double getTotalDiscount(){
        return getProductDiscountPrice() + this.orderDiscountPrice;
    }
    public Double getProductDiscountPrice(){
        return this.lines.stream()
                .mapToDouble(OrderLine::getDiscountPrice)
                .sum();
    }
    public Double getTotalPrice(){
        return getSubtotalPrice() + this.deliveryPrice - getTotalDiscount();
    }
}

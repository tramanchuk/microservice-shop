package com.example.products.model;

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
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;
    @Column
    String name;
    @Column
    Double price;
    @CreationTimestamp
    Date createdDate;
    @UpdateTimestamp
    Date lastUpdateDate;

    public Product(UUID id, String name, Double price) {
        this.name = name;
        this.price = price;
    }
}

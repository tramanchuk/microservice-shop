package com.example.myaggregator.model;

import java.util.Objects;
import java.util.List;

public class Order {
    Long id;
    String customerId;
    List<OrderLine> lines;

    public Order() {
    }

    public Order(String customerId, List<OrderLine> lines) {
        this.customerId = customerId;
        this.lines = lines;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public void setLines(List<OrderLine> lines) {
        this.lines = lines;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId='" + customerId + '\'' +
                ", lines=" + lines +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) && Objects.equals(customerId, order.customerId) && Objects.equals(lines, order.lines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, lines);
    }
}


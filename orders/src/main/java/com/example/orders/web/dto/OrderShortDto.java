package com.example.orders.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderShortDto {
    private Long orderId;
    Long customerId;
    Date createdDate;
}

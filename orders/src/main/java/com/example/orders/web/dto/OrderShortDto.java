package com.example.orders.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderShortDto {
    private UUID id;
    String customerId;
    Date createdDate;
}

package com.example.orders.web.dto;

import com.example.orders.model.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFullDto {
    @NotNull(groups = {OnPutUpdate.class, OnPatchUpdate.class})
    private UUID id;
    @NotNull(groups={OnPutUpdate.class, OnCreate.class})
    String customerId;
    List<OrderLineDto> lines;
    Date createdDate;
}

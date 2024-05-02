package com.example.orders.web.dto;

import com.example.orders.model.OrderLine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderFullDto {
    @NotNull(groups = {OnPutUpdate.class, OnPatchUpdate.class})
    private Long orderId;
    @NotNull(groups={OnPutUpdate.class, OnPatchUpdate.class, OnCreate.class})
    Long customerId;
    List<OrderLineDto> lines;
    Date createdDate;
}

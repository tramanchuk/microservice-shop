package com.example.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderFullDto {
    @NotNull(groups = {OnPutUpdate.class, OnPatchUpdate.class})
    private UUID id;
    @NotNull(groups={OnPutUpdate.class, OnCreate.class})
    String customerId;
    List<OrderLineDto> lines;
    Double subtotalPrice;
    Double deliveryPrice;
    Double discountPrice;
    Double totalPrice;
    Date createdDate;
}

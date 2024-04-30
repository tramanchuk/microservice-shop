package com.example.orders.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineDto {
    @NotNull(groups = {OnPutUpdate.class, OnPatchUpdate.class})
    Long id;
    @NotNull(groups={OnPutUpdate.class, OnPatchUpdate.class, OnCreate.class})
    String productId;
    @NotNull(groups={OnPutUpdate.class, OnPatchUpdate.class, OnCreate.class})
    Integer quantity;
}

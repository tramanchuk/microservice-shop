package com.example.orders.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineDto {
    @NotNull(groups = {OnPutUpdate.class, OnPatchUpdate.class})
    UUID id;
    @NotNull(groups={OnPutUpdate.class, OnPatchUpdate.class, OnCreate.class})
    String productId;
    @NotNull(groups={OnPutUpdate.class, OnPatchUpdate.class, OnCreate.class})
    Integer quantity;
}

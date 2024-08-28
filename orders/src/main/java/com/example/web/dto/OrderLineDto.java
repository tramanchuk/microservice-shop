package com.example.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderLineDto {
    @NotNull(groups = {OnPutUpdate.class, OnPatchUpdate.class})
    UUID id;
    @NotNull(groups={OnPutUpdate.class, OnCreate.class})
    String productId;
    @NotNull(groups={OnPutUpdate.class, OnCreate.class})
    Integer quantity;
    Double unitPrice;
    Double discountPrice;
}

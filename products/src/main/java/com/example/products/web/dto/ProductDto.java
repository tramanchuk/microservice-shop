package com.example.products.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    @NotNull(groups = {OnPutUpdate.class, OnPatchUpdate.class})
    UUID id;
    @NotNull(groups={OnPutUpdate.class, OnCreate.class})
    String name;
    @NotNull(groups={OnPutUpdate.class, OnCreate.class})
    Double price;
    Date createdDate;
    Date lastUpdateDate;
}

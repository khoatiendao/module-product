package com.example.module_product.module.product.dto.req;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class ProductReqDto {
    public record Create(
        @NotBlank @Size(max = 255) String name,
        @Positive BigDecimal price,
        @PositiveOrZero Integer quantity,
        @NotBlank @Size(max = 255) String category
    ) {}

    public record Update(
        @NotBlank @Size(max = 255) String name,
        @Positive BigDecimal price,
        @PositiveOrZero Integer quantity,
        @NotBlank @Size(max = 255) String category 
    ) {}
}

package com.example.module_product.module.product.dto.res;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.module_product.module.product.entity.Product;

public record ProductResDto(
    Long id,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Boolean active,
    String name,
    BigDecimal price,
    Integer quantity,
    String category
) {
    public static ProductResDto from(Product product) {
        return new ProductResDto(
                product.getId(),
                product.getCreatedAt(), 
                product.getUpdateAt(),
                product.getActive(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                product.getCategory()
        );
    }
}

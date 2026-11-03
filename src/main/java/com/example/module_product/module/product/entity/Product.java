package com.example.module_product.module.product.entity;

import java.math.BigDecimal;

import com.example.module_product.common.entity.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "products")
public class Product extends BaseEntity {
    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "price", nullable = false)
    @Positive
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    @PositiveOrZero
    private Integer quantity;

    @Column(name = "category", nullable = false)
    private String category;
}

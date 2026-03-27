package com.keshavbansal.ECommerceStore.request;

import com.keshavbansal.ECommerceStore.model.Category;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private Long id;

    private String name;

    private String brand;

    private String description;

    private BigDecimal price;

    private int quantity;

    private Category category;
}

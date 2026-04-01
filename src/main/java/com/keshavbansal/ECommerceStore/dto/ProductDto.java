package com.keshavbansal.ECommerceStore.dto;

import com.keshavbansal.ECommerceStore.model.Category;
import com.keshavbansal.ECommerceStore.model.Images;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;

    private String name;

    private String brand;

    private String description;

    private BigDecimal price;

    private int quantity;

    private Category category;

    private List<ImageDto> images;

}

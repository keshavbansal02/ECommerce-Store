package com.keshavbansal.ECommerceStore.repository;

import com.keshavbansal.ECommerceStore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByBrand(String brand);

    List<Product> findByName(String name);

    List<Product> findByBrandAndCategoryName(String brand, String category);

    List<Product> findByBrandAndName(String brand, String name);

    List<Product> findByCategoryName(String categoryName);

    Long countByCategoryName(String category);

    Long countByBrand(String brand);

    Long countByName(String name);

    Long countByBrandAndName(String brand, String name);
}
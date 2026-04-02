package com.keshavbansal.ECommerceStore.repository;

import com.keshavbansal.ECommerceStore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    void deleteAllByCartId(Long id);
}
package com.keshavbansal.ECommerceStore.repository;

import com.keshavbansal.ECommerceStore.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
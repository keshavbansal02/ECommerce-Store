package com.keshavbansal.ECommerceStore.repository;

import com.keshavbansal.ECommerceStore.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {
}
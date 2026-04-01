package com.keshavbansal.ECommerceStore.repository;

import com.keshavbansal.ECommerceStore.model.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {
    List<Images> findByProductId(Long id);
}
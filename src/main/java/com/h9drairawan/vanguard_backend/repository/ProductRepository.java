package com.h9drairawan.vanguard_backend.repository;

import com.h9drairawan.vanguard_backend.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

package com.example.demo.repository;

import com.example.demo.model.abandoned.ProductOrderMap;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderMapRepository extends JpaRepository<ProductOrderMap,Integer> {
}

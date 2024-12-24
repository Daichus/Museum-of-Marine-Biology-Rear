package com.example.demo.repository;

import com.example.demo.model.abandoned.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}

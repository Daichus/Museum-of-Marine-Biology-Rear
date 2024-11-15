package com.example.demo.repository;

import com.example.demo.model.ProductOrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductOrderPaymentRepository extends JpaRepository<ProductOrderPayment,Integer> {
}

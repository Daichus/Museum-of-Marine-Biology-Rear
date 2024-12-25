package com.example.demo.repository.product;

import com.example.demo.model.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductOrderRepository extends JpaRepository<ProductOrder,Integer> {

   List<ProductOrder> findByPhone(String phone);
}

package com.example.demo.model.abandoned;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name="ProductOrderMap")
public class ProductOrderMap {

    @Id
    @Column(name="order_id")
    private int order_id;

    @Column(name="product_id")
    private int product_id;

    @Column(name="quantity")
    private int quantity;





}

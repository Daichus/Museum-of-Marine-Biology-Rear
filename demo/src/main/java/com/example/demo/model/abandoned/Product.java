package com.example.demo.model.abandoned;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name="Product")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name ="product_id")
    private int product_id;

    @Column (name = "name")
    private String name;

    @Column(name="price")
    private int price;
}

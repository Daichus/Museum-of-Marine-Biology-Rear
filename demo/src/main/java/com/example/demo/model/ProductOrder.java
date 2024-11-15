package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Table(name="ProductOrder")
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private int order_id;

    @Column(name="user_id")
    private int user_id;

    @Column(name="order_time")
    private LocalDateTime order_time;




}

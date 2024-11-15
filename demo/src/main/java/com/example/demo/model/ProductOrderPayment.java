package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Table(name="ProductOrderPayment")
public class ProductOrderPayment {

    @Id
    @Column(name="payment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int payment_id;

    @Column(name="order_id")
    private int order_id;

    @Column(name="user_id")
    private int user_id;

    @Column(name="payment_date")
    private LocalDateTime payment_data;

    @Column(name="payment_amount")
    private double payment_amount;

    @Column(name="payment_method")
    private String payment_method;

    @Column(name="status")
    private String status;


}

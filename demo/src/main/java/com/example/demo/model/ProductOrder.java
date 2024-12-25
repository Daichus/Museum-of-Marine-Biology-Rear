package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ProductOrder")
public class ProductOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_order_id")
    private int product_order_id;

    @Column(name = "email")
    private String email;

    @Column(name= "id_number")
    private String id_number;

    @Column(name="name")
    private String name;

    @Column(name="phone")
    private String phone;

    @Column(name="order_time")
    private LocalDateTime order_time;

    @Column(name="verifyCode")
    private String verifyCode;

    @OneToMany(mappedBy = "productOrder" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderProducts> orderProducts;




}

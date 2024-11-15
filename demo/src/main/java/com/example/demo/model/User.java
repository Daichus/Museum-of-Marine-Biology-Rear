package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
    private int user_id;

    @Column(name="id_number")
    private String id_number;

    @Column(name="name")
    private String name;

    @Column(name="phone")
    private String phone;

    @Column(name = "email")
    private String email;

}

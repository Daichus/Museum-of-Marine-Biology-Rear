package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="TicketOrders")
public class TicketOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ticket_order_id")
    private int ticket_order_id;


   @Column(name = "email")
    private String email;

   @Column(name= "id_number")
    private String id_number;

   @Column(name="name")
    private String name;

   @Column(name="phone")
    private String phone;



   @JsonFormat(pattern = "yyyy-MM-dd")
   @Column(name="visit_time")
    private LocalDate visit_time;

    @Column(name="verifyCode")
    private String verifyCode;

    @OneToMany(mappedBy = "ticketOrder" , cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrderTickets> orderTickets;

}

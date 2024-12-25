package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="OrderTickets")
public class OrderTickets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_ticket_id")
    private int order_ticket_id;

    @ManyToOne
    @JoinColumn(name = "ticket_order_id")
    @JsonBackReference
    private TicketOrder ticketOrder;

    @Column(name="type")
    private String type;

    @Column(name="quantity")
    private int quantity;

    @Column(name="price")
    private int price;
}

package com.example.demo.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Table(name="TicketOrderMap")
public class TicketOrderMap {

    @Id
    @Column(name="order_id")
    private int order_id;

    @Column(name="ticket_id")
    private int ticket_id;

    @Column(name="quantity")
    private int quantity;
}

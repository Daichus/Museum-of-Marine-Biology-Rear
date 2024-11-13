package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Table(name="TicketRefund")
public class TicketRefund {


    @Id
    @Column(name="refund_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int refund_id;

    @Column(name="ticket_order_id")
    private int ticket_order_id;

    @Column(name="refund_date")
    private LocalDateTime refund_data;

    @Column(name="status")
    private String stats;
}

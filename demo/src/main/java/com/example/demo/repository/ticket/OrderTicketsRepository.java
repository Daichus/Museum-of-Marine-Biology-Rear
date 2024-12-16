package com.example.demo.repository.ticket;

import com.example.demo.model.OrderTickets;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTicketsRepository extends JpaRepository<OrderTickets,Integer> {
}

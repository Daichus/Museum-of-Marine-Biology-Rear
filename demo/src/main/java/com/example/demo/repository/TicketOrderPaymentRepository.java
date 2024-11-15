package com.example.demo.repository;

import com.example.demo.model.TicketOrderPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketOrderPaymentRepository extends JpaRepository<TicketOrderPayment,Integer> {
}

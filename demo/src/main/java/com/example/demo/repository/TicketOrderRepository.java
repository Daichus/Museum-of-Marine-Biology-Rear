package com.example.demo.repository;


import com.example.demo.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketOrderRepository extends JpaRepository<TicketOrder,Integer> {


}

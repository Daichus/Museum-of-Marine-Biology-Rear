package com.example.demo.repository.ticket;


import com.example.demo.model.ProductOrder;
import com.example.demo.model.TicketOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketOrderRepository extends JpaRepository<TicketOrder,Integer> {
    List<TicketOrder> findByPhone(String phone);

}

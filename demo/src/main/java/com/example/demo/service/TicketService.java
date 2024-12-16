package com.example.demo.service;



import com.example.demo.model.OrderTickets;
import com.example.demo.model.TicketOrder;
import com.example.demo.model.dto.OrderDTO;

import com.example.demo.repository.ticket.OrderTicketsRepository;
import com.example.demo.repository.ticket.TicketOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    TicketOrderRepository ticketOrderRepository;

    @Autowired
    OrderTicketsRepository orderTicketsRepository;

    public ResponseEntity<?> createTicketOrder (OrderDTO dto){
        TicketOrder ticketOrder = new TicketOrder();
        ticketOrder.setEmail(dto.getEmail());
        ticketOrder.setId_number(dto.getId_number());
        ticketOrder.setName(dto.getName());
        ticketOrder.setPhone(dto.getPhone());
        ticketOrder.setVisit_time(dto.getDate());
        List<OrderTickets> tickets = dto.getTickets().stream().
            filter(ticketDTO -> ticketDTO.getQuantity() > 0).
            map(ticketDTO -> {
            OrderTickets ticket = new OrderTickets();
            ticket.setType(ticketDTO.getType());
            ticket.setQuantity(ticketDTO.getQuantity());
            ticket.setPrice(ticketDTO.getPrice());
            ticket.setTicketOrder(ticketOrder); // 關聯到主訂單
            return ticket;
        }).collect(Collectors.toList());

        ticketOrder.setOrderTickets(tickets);

        // 儲存訂單及其票務數據
        ticketOrderRepository.save(ticketOrder);

        return ResponseEntity.ok().body("訂單儲存成功");

    }




}

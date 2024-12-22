package com.example.demo.controller;


import com.example.demo.model.dto.TicketOrderDTO;
import com.example.demo.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    TicketService ticketService;


     @PostMapping("/orderTicket")
    public ResponseEntity<?> orderTicket (@RequestBody TicketOrderDTO dto) {
         return ticketService.createTicketOrder(dto);
     }
}

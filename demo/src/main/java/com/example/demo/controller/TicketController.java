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

    @PostMapping("/getOrder/{phone}")
    public ResponseEntity<?> getOrderByPhone(@PathVariable String phone, @RequestBody String verifyCode) {
        System.out.println(verifyCode);
        return ticketService.getOrderByPhone(phone, verifyCode);
    }

     @PostMapping("/orderTicket")
    public ResponseEntity<?> orderTicket (@RequestBody TicketOrderDTO dto) {
         return ticketService.createTicketOrder(dto);
     }

     @GetMapping("/getAllOrder")
    public ResponseEntity<?>getAllOrder(){
         return ticketService.getAllOrder();
     }
}

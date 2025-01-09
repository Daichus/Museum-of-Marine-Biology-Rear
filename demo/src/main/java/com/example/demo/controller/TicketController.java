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
     @PutMapping("/editOrder/{order_id}")
     public ResponseEntity<?> editOrder(@PathVariable int order_id, @RequestBody TicketOrderDTO dto) {
        return ticketService.editOrder(order_id,dto);
     }


     @GetMapping("/getAllOrder")
    public ResponseEntity<?>getAllOrder(){
         return ticketService.getAllOrder();
     }

     @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable int id){
         return ticketService.deleteOrder(id);
     }
}

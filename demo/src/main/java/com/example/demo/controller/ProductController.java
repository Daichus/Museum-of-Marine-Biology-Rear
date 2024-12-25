package com.example.demo.controller;


import com.example.demo.model.dto.ProductOrderDto;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/getAllOrder")
    public ResponseEntity<?> getAllOrder(){
        return service.getAllOrder();
    }

    @GetMapping("/getOrder/{phone}")
    public ResponseEntity<?> getOrderByPhone (@PathVariable String phone, String verifyCode) {
        return service.getOrderByPhone(phone, verifyCode);
    }

    @PostMapping("/createOrder")
        public ResponseEntity<?> createOrder(@RequestBody ProductOrderDto dto) {
        return service.createOrder(dto);
    }

}

package com.example.demo.controller;


import com.example.demo.model.dto.ProductOrderDto;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService service;

    @PostMapping("/createOrder")
        public ResponseEntity<?> createOrder(@RequestBody ProductOrderDto dto) {
        return service.createOrder(dto);
    }

}

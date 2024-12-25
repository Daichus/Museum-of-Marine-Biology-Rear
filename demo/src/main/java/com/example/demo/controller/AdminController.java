package com.example.demo.controller;


import com.example.demo.model.dto.LoginDto;
import com.example.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @Autowired
    AdminService service;


    @PostMapping("/login")
    public ResponseEntity<?> login (@RequestBody LoginDto dto) {
        return service.login(dto);
    }
}

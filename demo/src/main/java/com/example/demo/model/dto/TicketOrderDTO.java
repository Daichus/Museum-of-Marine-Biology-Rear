package com.example.demo.model.dto;

import com.example.demo.model.Tickets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class TicketOrderDTO {

    private String id_number;

    private String name;

    private String phone;

    private String email;

    private LocalDate date;

    private List<Tickets> tickets;


}

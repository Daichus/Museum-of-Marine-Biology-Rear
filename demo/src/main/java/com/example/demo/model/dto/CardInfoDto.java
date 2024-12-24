package com.example.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardInfoDto {

    private String cardNumber;

    private String cvv;

    private String expiryDate;
}

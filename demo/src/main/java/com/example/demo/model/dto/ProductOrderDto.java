package com.example.demo.model.dto;


import com.example.demo.model.OrderProducts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductOrderDto {

    private PaymentInfoDto paymentInfo;

    private List<OrderProducts> cartItems;

    private CardInfoDto cardInfo;

    private String paymentMethod;
}

package com.example.demo.service;


import com.example.demo.model.OrderProducts;
import com.example.demo.model.ProductOrder;
import com.example.demo.model.dto.CardInfoDto;
import com.example.demo.model.dto.PaymentInfoDto;
import com.example.demo.model.dto.ProductOrderDto;
import com.example.demo.repository.ProductOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductOrderRepository repository;

    @Autowired
    NotificationService notificationService;

    public ResponseEntity<?> createOrder (ProductOrderDto dto) {
        PaymentInfoDto paymentInfo = dto.getPaymentInfo();
        String paymentMethod =dto.getPaymentMethod();
        if ("visa".equals(paymentMethod) || "paypal".equals(paymentMethod)) {
            CardInfoDto cardInfo = dto.getCardInfo();
            if (cardInfo == null || cardInfo.getCardNumber() == null || cardInfo.getCvv() == null || cardInfo.getExpiryDate() == null) {
                return ResponseEntity.badRequest().body("信用卡資訊不完整！");
            }
            String cardNumber = cardInfo.getCardNumber().replaceAll("\\s", "");
            if (!cardNumber.matches("^\\d{16}$")) {
                return ResponseEntity.badRequest().body("信用卡號格式不正確！應為16位數字且不包含空格。");
            }
            if (!cardInfo.getCvv().matches("^\\d{3}$")) {
                return ResponseEntity.badRequest().body("CVV 格式不正確！應為3位數字。");
            }
            if (!cardInfo.getExpiryDate().matches("^(0[1-9]|1[0-2])\\/\\d{2}$")) {
                return ResponseEntity.badRequest().body("信用卡過期時間格式不正確！應為MM/YY格式。");
            }

        }   else {
            return ResponseEntity.badRequest().body("不支持的付款方式！");
        }

        ProductOrder order = mapToEntity(dto);
        repository.save(order);
        if (paymentInfo.getEmail() != null) {
            notificationService.sendEmail(paymentInfo.getEmail(), "付款成功通知", "您的訂單已成功付款！");
        } else if (paymentInfo.getPhone() != null) {
            System.out.println("向電話 " + paymentInfo.getPhone() + " 發送短信通知：您的訂單已成功付款！");
        }
        return ResponseEntity.ok("創建訂單成功");
    }

    private ProductOrder mapToEntity(ProductOrderDto dto) {
        ProductOrder order = new ProductOrder();
        order.setPhone(dto.getPaymentInfo().getPhone());
        order.setName(dto.getPaymentInfo().getName());
        order.setId_number(dto.getPaymentInfo().getId_number());
        order.setOrder_time(LocalDateTime.now());

        if(dto.getPaymentInfo().getEmail() != null){
            order.setEmail(dto.getPaymentInfo().getEmail());
        }

        // 轉換 CartItems
        List<OrderProducts> orderProducts = dto.getCartItems().stream().map(cartItem -> {
            OrderProducts orderProduct = new OrderProducts();
            orderProduct.setTitle(cartItem.getTitle());
            orderProduct.setPrice(cartItem.getPrice());
            orderProduct.setQuantity(cartItem.getQuantity());
            orderProduct.setTotal(cartItem.getTotal());
            orderProduct.setProductOrder(order);
            return orderProduct;
        }).toList();

        order.setOrderProducts(orderProducts);
        return order;
    }


}

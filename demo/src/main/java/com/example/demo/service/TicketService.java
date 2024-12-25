package com.example.demo.service;



import com.example.demo.model.OrderTickets;
import com.example.demo.model.ProductOrder;
import com.example.demo.model.TicketOrder;
import com.example.demo.model.dto.CardInfoDto;
import com.example.demo.model.dto.TicketOrderDTO;

import com.example.demo.repository.ticket.OrderTicketsRepository;
import com.example.demo.repository.ticket.TicketOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    @Autowired
    TicketOrderRepository ticketOrderRepository;



    @Autowired
    NotificationService notificationService;

    public ResponseEntity<?> getAllOrder(){
        List<TicketOrder> orders = ticketOrderRepository.findAll();
        return ResponseEntity.ok(orders);
    }

    public ResponseEntity<?>getOrderByPhone(String phone, String verifyCode) {
        List<TicketOrder> orders = ticketOrderRepository.findByPhone(phone);
        TicketOrder matchingOrder = null;
        for (TicketOrder order : orders) {
            System.out.println(order.getVerifyCode());
            if (verifyCode.equals(order.getVerifyCode().trim())) {
                matchingOrder = order;
                break;
            }
        }
        if(matchingOrder != null) {
            return ResponseEntity.ok(matchingOrder);
        } else {
            System.out.println("找不到匹配結果");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("找不到匹配結果");
        }
    }

    public ResponseEntity<?> createTicketOrder (TicketOrderDTO dto){
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

        TicketOrder ticketOrder = new TicketOrder();
        ticketOrder.setEmail(dto.getEmail());
        ticketOrder.setId_number(dto.getId_number());
        ticketOrder.setName(dto.getName());
        ticketOrder.setPhone(dto.getPhone());
        ticketOrder.setVisit_time(dto.getDate());
        ticketOrder.setVerifyCode(generateVerificationCode());
        List<OrderTickets> tickets = dto.getTickets().stream().
            filter(ticketDTO -> ticketDTO.getQuantity() > 0).
            map(ticketDTO -> {
            OrderTickets ticket = new OrderTickets();
            ticket.setType(ticketDTO.getType());
            ticket.setQuantity(ticketDTO.getQuantity());
            ticket.setPrice(ticketDTO.getPrice());
            ticket.setTicketOrder(ticketOrder); // 關聯到主訂單
            return ticket;
        }).collect(Collectors.toList());

        ticketOrder.setOrderTickets(tickets);

        // 儲存訂單及其票務數據
        ticketOrderRepository.save(ticketOrder);

        if(dto.getEmail() !=  null) {
            String verifyCode = ticketOrder.getVerifyCode();
            String emailMessage = String.format("您的訂單已成功付款！\n驗證碼為：%s", verifyCode);
            notificationService.sendEmail(ticketOrder.getEmail(), "付款成功通知", emailMessage);
        }else if (dto.getPhone() != null) {
            System.out.println("向電話 " + dto.getPhone() + " 發送短信通知：您的訂單已成功付款！");
        }
        return ResponseEntity.ok("創建訂單成功");
        
    }

        private static String generateVerificationCode() {
            final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
            final int codeLength = 20;
            SecureRandom random = new SecureRandom();
            StringBuilder verificationCode = new StringBuilder(codeLength);

            for (int i = 0; i < codeLength; i++) {
                int randomIndex = random.nextInt(characters.length());
                verificationCode.append(characters.charAt(randomIndex));
            }
            return verificationCode.toString();
        }



}

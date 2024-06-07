package com.example.demo.user_order.controller;

import com.example.demo.user_order.userorderDTO.PaymentDTO;
import com.example.demo.user_order.userorderService.PaymentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/process")
    public ResponseEntity<String> processPayment(HttpSession session, @RequestBody PaymentDTO paymentDTO) {
        // 세션에서 사용자 ID 가져오기
        String userId = (String) session.getAttribute("loginId");
        if (userId == null) {
            return ResponseEntity.status(401).body("{\"message\": \"User not authenticated\"}");
        }

        // PaymentDTO에 사용자 ID 설정
        paymentDTO.setId(userId);

        // 결제 처리
        try {
            paymentService.processPayment(paymentDTO);
            return ResponseEntity.ok("{\"message\": \"Payment processed successfully\"}");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("{\"message\": \"Payment processing failed\"}");
        } }

}

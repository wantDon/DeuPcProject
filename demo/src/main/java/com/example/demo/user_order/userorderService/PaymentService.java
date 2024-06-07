package com.example.demo.user_order.userorderService;

import com.example.demo.user_order.userorderDTO.PaymentDTO;

public interface PaymentService {
    void processPayment(PaymentDTO paymentDTO);
}

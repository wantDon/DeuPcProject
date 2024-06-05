package com.example.demo.user_order.userorderService;

import com.example.demo.user_order.userorderDTO.PaymentDTO;
import com.example.demo.user_order.userorderDTO.OrderDTO;
import com.example.demo.user_order.userorderMapper.PaymentMapper;
import com.example.demo.user_order.userorderService.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PaymentServiceImple implements PaymentService {

    @Autowired
    private PaymentMapper paymentMapper;

    @Override
    @Transactional
    public void processPayment(PaymentDTO paymentDTO) {
        // Save payment
        paymentMapper.savePayment(paymentDTO);

        // Save orders
        List<OrderDTO> orders = paymentDTO.getOrders();
        for (OrderDTO order : orders) {
            paymentMapper.saveOrder(order);
        }
    }
}

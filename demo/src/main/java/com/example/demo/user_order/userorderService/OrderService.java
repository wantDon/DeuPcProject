package com.example.demo.user_order.userorderService;

import com.example.demo.user_order.userorderDTO.OrderDTO;
import com.example.demo.user_order.userorderDTO.PaymentDTO;

public interface OrderService {
    void processOrder(OrderDTO orderDTO);
}

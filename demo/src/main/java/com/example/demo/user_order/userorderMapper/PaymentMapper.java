package com.example.demo.user_order.userorderMapper;

import com.example.demo.user_order.userorderDTO.OrderDTO;
import com.example.demo.user_order.userorderDTO.PaymentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
public interface PaymentMapper {
    void savePayment(PaymentDTO paymentDTO);
    void saveOrder(OrderDTO orderDTO);
}

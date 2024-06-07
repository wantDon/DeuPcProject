package com.example.demo.user_order.userorderService;


import com.example.demo.user_order.userorderDTO.OrderDTO;
import com.example.demo.user_order.userorderMapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImple implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public void processOrder(OrderDTO orderDTO){

        orderMapper.saveOrder(orderDTO);
    }

}

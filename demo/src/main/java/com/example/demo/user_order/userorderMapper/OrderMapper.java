package com.example.demo.user_order.userorderMapper;

import com.example.demo.user_order.userorderDTO.OrderDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    void saveOrder(OrderDTO orderDTO);
}

package com.example.demo.user_order.userorderService;

import com.example.demo.user_order.userorderDTO.foodDTO;
import com.example.demo.user_order.userorderMapper.foodsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class foodsServiceImple implements foodService {

    @Autowired
    private foodsMapper foodMapper;

    @Override
    public List<foodDTO> getAllfood() {
        return foodMapper.getAllfood();
    }
}
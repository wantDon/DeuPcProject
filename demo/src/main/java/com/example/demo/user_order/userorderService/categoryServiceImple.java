package com.example.demo.user_order.userorderService;

import com.example.demo.user_order.userorderDTO.categoryDTO;
import com.example.demo.user_order.userorderMapper.categoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class categoryServiceImple implements categoryService {

    @Autowired
    private categoryMapper categoryMapper;

    @Override
    public List<categoryDTO> getAllCategories() {
        return categoryMapper.getAllCategories();
    }
}
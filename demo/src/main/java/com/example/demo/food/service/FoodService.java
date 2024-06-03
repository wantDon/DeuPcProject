package com.example.demo.food.service;

import java.io.IOException;
import java.util.List;

import com.example.demo.food.dto.CategoryDTO;
import com.example.demo.food.dto.FoodDTO;

public interface FoodService {
    //Food====================
    List<FoodDTO> getListFood();

    void saveFood(FoodDTO foodDTO) throws IOException;

    void food_refix_request(FoodDTO foodDTO);

    //Category=======================
    List<CategoryDTO> selectAllCategoryDTO();
}

package com.example.demo.food.mapper;

import java.util.List;

import com.example.demo.food.dto.CategoryDTO;
import org.apache.ibatis.annotations.Mapper;

import com.example.demo.food.dto.FoodDTO;

@Mapper
public interface FoodMapper {

    List<FoodDTO> getAllFoods();

    void insertFood(FoodDTO foodDTO);

    void refix_request(FoodDTO foodDTO);

    //Category=====================
    List<CategoryDTO> selectAllCategory();

}

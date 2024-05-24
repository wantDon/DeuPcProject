package com.example.demo.food.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.food.dto.FoodDTO;

@Mapper
public interface FoodMapper {

	List<FoodDTO> getAllFoods();

	void insertFood(FoodDTO foodDTO);
}

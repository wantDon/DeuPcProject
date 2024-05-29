package com.example.demo.food.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.food.dto.FoodDTO;
import com.example.demo.food.mapper.FoodMapper;

@Service
public class FoodServiceImple implements FoodService{
	
	private FoodMapper foodMapper;
	
	public FoodServiceImple(FoodMapper foodMapper) {
		this.foodMapper = foodMapper;
	}

    @Override
    public List<FoodDTO> getListFood() {
        return foodMapper.getAllFoods();
    }

    @Override
    public void saveFood(FoodDTO foodDTO) throws IOException {
        // FoodDTO를 Food 엔티티로 변환하여 Mapper를 통해 데이터베이스에 저장
        foodMapper.insertFood(foodDTO);
    }

}

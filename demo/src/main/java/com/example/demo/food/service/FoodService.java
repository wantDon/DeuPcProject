package com.example.demo.food.service;

import java.io.IOException;
import java.util.List;

import com.example.demo.food.dto.FoodDTO;
import org.springframework.web.multipart.MultipartFile;

public interface FoodService {
	
	List<FoodDTO> getListFood();

	void saveFood(FoodDTO foodDTO) throws IOException;

}
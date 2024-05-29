package com.example.demo.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.food.dto.FoodDTO;
import com.example.demo.food.service.FoodServiceImple;

@Controller
@RequestMapping("/food")
public class FoodController {

	private FoodServiceImple foodService;
	
	@Autowired
	public FoodController(FoodServiceImple foodService) {
		this.foodService = foodService;
	}
	
	// 상품 페이지로 이동
    @GetMapping
    public String showFoodPage() {
        return "food/food";
    }
	
	// 상품 등록 페이지로 이동
    @GetMapping("/food_register")
    public String showRegisterForm() {
        return "food/food_register";
    }
    
    // 세트상품 등록 페이지로 이동
    @GetMapping("/food_set")
    public String showSetForm(Model model) {
        // 상품 리스트를 가져와서 모델에 추가
        List<FoodDTO> foodList = foodService.getListFood();
        model.addAttribute("foodList", foodList);
        return "food/food_set";
    }
	
	@PostMapping("/save")
	public String saveFood(FoodDTO foodDTO, Model model) {
	    try {
	        foodService.saveFood(foodDTO); // FoodService를 통해 데이터베이스에 저장
	    } catch (Exception e) {
	        e.printStackTrace();
	        model.addAttribute("errorMessage", "상품 저장 중 오류가 발생했습니다.");
	        return "/food/food_register"; // 오류 발생 시 다시 상품 등록 페이지로 이동
	    }
	    return "redirect:/food"; // 상품 저장 후 상품 등록 페이지로 리다이렉트
	}

}

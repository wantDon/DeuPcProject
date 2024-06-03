package com.example.demo.food.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.food.dto.FoodDTO;
import com.example.demo.food.service.FoodServiceImple;
import java.util.List;

import com.example.demo.food.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.food.dto.FoodDTO;
import com.example.demo.food.service.FoodServiceImple;
import org.springframework.web.bind.annotation.RequestParam;

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

    //상품수정 페이지 이동
    @GetMapping("/food_refix")
    public String food_refix(Model model) {
        List<FoodDTO> foodDTOs = foodService.getListFood();
        List<CategoryDTO> categoryDTOs = foodService.selectAllCategoryDTO();

        model.addAttribute("foodDTOs", foodDTOs);
        model.addAttribute("categoryDTOs", categoryDTOs);

        return "/food/food_refix";
    }

    @GetMapping("/food_refix/request")
    public String itemRefixRequest(
            @RequestParam("num") String num, @RequestParam("name") String name, @RequestParam("price") String price,
            @RequestParam("count") String count, @RequestParam("state") String state) {
        //1. 유효성 검사 2. dto대입 3.update해주기
        boolean trigger = true;
        if (num != null && name != null && price != null && count != null && state != null) {
            //유효성 검사
            try {
                Integer.parseInt(price);
                Integer.parseInt(count);
                Integer.parseInt(state);
            } catch (NumberFormatException e) {
                trigger = false;
            }
        } else {
            trigger = false;
        }
        if (trigger == false) return "redirect:/food/food_refix";//유효값이 아닐시 그냥 무시하고 원래홈페이지로 보내버린다.
        //2.dto생성하고 대입하기
        System.out.println(num);
        System.out.println(name);
        System.out.println(price);
        System.out.println(count);
        System.out.println(state);
        FoodDTO foodDTO = new FoodDTO();
        foodDTO.setFood_num(Integer.parseInt(num));
        foodDTO.setFood_name(name);
        foodDTO.setFood_price(Integer.parseInt(price));
        foodDTO.setCount(Integer.parseInt(count));
        foodDTO.setState(Integer.parseInt(state));

        //3.update해주기
        foodService.food_refix_request(foodDTO);

        return "redirect:/food/food_refix";
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

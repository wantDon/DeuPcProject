package com.example.demo.user_order.controller;

import com.example.demo.user_order.userorderDTO.categoryDTO;
import com.example.demo.user_order.userorderDTO.foodDTO;
import com.example.demo.user_order.userorderService.categoryService;
import com.example.demo.user_order.userorderService.foodService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class userorderController {
    @Autowired
    private categoryService categoryService;

    @Autowired
    private foodService foodService;


    @GetMapping("/userorder")
    public String showUserOrder(HttpSession session, Model model){
        String userId = (String) session.getAttribute("loginId");
        if(userId !=null){
            List<categoryDTO> categories = categoryService.getAllCategories();
            List<foodDTO> food = foodService.getAllfood();

            model.addAttribute("categories", categories);
            model.addAttribute("food", food);
            return "userorder/userorder";
        }else{
            return "redirect:login";
        }
    }
}

package com.example.demo.myprofile.controller;

import com.example.demo.myprofile.myprofileDTO.paymentDTO;
import com.example.demo.myprofile.myprofileDTO.userDTO;
import com.example.demo.myprofile.myprofileService.myorderService;
import com.example.demo.myprofile.myprofileService.userService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class myorderController {

    @Autowired
    private myorderService myorderService;

    @PostMapping("/myorder")
    public String getUserOrders(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        System.out.println(userId);
        if (userId != null) {

            List<paymentDTO> userOrders = myorderService.getUserOrders(userId);
            model.addAttribute("userOrders", userOrders);
            return "myprofile/myprofile";
        }else{
            return "redirect:/login";
        }

    }

}

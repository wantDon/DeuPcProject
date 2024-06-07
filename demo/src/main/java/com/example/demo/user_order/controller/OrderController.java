package com.example.demo.user_order.controller;

import com.example.demo.user_order.userorderDTO.OrderDTO;
import com.example.demo.user_order.userorderService.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/process")
    public ResponseEntity<String> processOrder(HttpSession session, @RequestBody OrderDTO orderDTO){
        String userId = (String) session.getAttribute("loginId");
        if(userId ==null){
            return ResponseEntity.status(401).body("{\"message\": \"User not authenticated\"}");
        }

        orderService.processOrder(orderDTO);

        return ResponseEntity.ok("{\"message\": \"Payment processed successfully\"}");
    }
}

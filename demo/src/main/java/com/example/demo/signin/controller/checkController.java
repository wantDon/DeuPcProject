package com.example.demo.signin.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import com.example.demo.signin.service.SigninService;

@RestController
public class checkController {
	
	@Autowired
	private SigninService signinService;
	public checkController() {
		
	}
	
	@RequestMapping(value="/pc/check_id/{id}")
	public Map<String, Object> check_id(@PathVariable String id, HttpSession session) {
		 Map<String, Object> response = new HashMap<>();
		boolean idresult = signinService.idOverlap(id);
		if (idresult) {
            session.setAttribute("idcheck", "yes");
            response.put("available", true);
            response.put("message", "사용 가능한 ID입니다.");
        } else {
            session.setAttribute("idcheck", "no");
            response.put("available", false);
            response.put("message", "이미 사용 중인 ID입니다.");
        }

        return response;
	}
	
	
}
	

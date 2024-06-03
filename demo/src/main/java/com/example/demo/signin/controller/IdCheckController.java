package com.example.demo.signin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.signin.service.SigninService;

import jakarta.servlet.http.HttpSession;

@RestController
public class IdCheckController {
	
	@Autowired
	private SigninService signinService;
	public IdCheckController(SigninService signinService) {
		this.signinService = signinService;
	}
	
	@RequestMapping(value="/pc/check_id/{id}")
	public boolean check_id(@PathVariable String id, HttpSession session) {
		session.setAttribute("idcheck", "no");
		boolean result = signinService.idOver(id);
		return result;
	}
	
}
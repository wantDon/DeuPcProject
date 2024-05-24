package com.example.demo.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/pc/admin")
public class AdminController {
	
	public AdminController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value={"", "/"})
	public String AdminMain() {
		return "admin/index";
	}
	
}
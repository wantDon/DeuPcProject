package com.example.demo.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.admin.dto.LoginTimeDTO;
import com.example.demo.admin.service.AdminServiceImple;

@RestController
@RequestMapping(value="/pc/admin")
public class UserTimeController {

	@Autowired
	private AdminServiceImple adminService;
	

	@GetMapping(value="/ulist")
	public List<LoginTimeDTO> getLoginUser() {
		return adminService.getAllUse();
	}
	
}

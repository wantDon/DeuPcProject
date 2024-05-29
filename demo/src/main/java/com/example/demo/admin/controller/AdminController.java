package com.example.demo.admin.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Vector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.admin.dto.MemberDTO;
import com.example.demo.admin.service.AdminServiceImple;

@Controller
@RequestMapping(value="/pc/admin")
public class AdminController {
	
	@Autowired
	private AdminServiceImple adminService;
	
	public AdminController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value={"", "/"})
	public String AdminMain() {
		return "admin/index";
	}
	
	@GetMapping("/member")
	public String AdminMember(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, Model model) {
		Pageable pageable = PageRequest.of(page, size);
		Page<MemberDTO> pageResult = adminService.getAllUser(pageable);
		
		model.addAttribute("memberList", pageResult.getContent());
		model.addAttribute("currentPage", pageResult.getNumber());
		model.addAttribute("totalPages", pageResult.getTotalPages());
		
		return "admin/member_info";
	}
	
}
package com.example.demo.signin.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.signin.dto.SigninDTO;
import com.example.demo.signin.service.SigninServiceImple;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/pc")
public class SigninController {
	
	private SigninServiceImple signinService;
	public SigninController(SigninServiceImple signinService) {
		this.signinService = signinService;
	}
	
    @GetMapping(value = "/signin")
    public String selectDay(Model model) {
        List<String> days = IntStream.rangeClosed(1, 31)
        		.mapToObj(day -> String.format("%02d", day)).collect(Collectors.toList());
        model.addAttribute("days", days);
        List<String> months = IntStream.rangeClosed(1, 12)
        		.mapToObj(month -> String.format("%02d", month)).collect(Collectors.toList());
        model.addAttribute("months", months);
        List<String> years = IntStream.rangeClosed(1900, 2024)
        		.mapToObj(year -> String.format("%04d", year)).collect(Collectors.toList());
        model.addAttribute("years", years);
        List<String> emailDomains = Arrays.asList("naver.com", "gmail.com","nate.com","daum.net", "yahoo.com");
        model.addAttribute("emailDomains",emailDomains);
        return "signin/signin";
    }
	
    @PostMapping(value="/signin")
    public String signin(SigninDTO signinDTO, HttpSession session) {
    	String check = (String) session.getAttribute("idcheck");
    	
    	if (check == null || check.equals("no")) {
    		System.out.println("s");
    	}
    	return "redirect:/pc/signin";
    }
	
}
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import com.example.demo.signin.dto.SigninDTO;
import com.example.demo.signin.service.SigninServiceImple;

@Controller
@RequestMapping(value = "pc")
public class SigninController {

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
    private SigninServiceImple signinService;
    
	public SigninController(SigninServiceImple signinService) {
		this.signinService = signinService;
	}
	
	
    @PostMapping(value="/signin")
    public String signin(SigninDTO signinDTO, HttpSession session, RedirectAttributes r) {
    	String idCheck = (String) session.getAttribute("idcheck");
    	boolean signin = signinService.Signin(signinDTO);
    	
    	if(!signin) {
    		r.addFlashAttribute("messageType", "error");
    		r.addFlashAttribute("message", "회원가입에 실패하였습니다.");
    		return "redirect:/pc/signin";
    	}
    	
    	r.addFlashAttribute("messageType", "success");
    	r.addFlashAttribute("message", "회원가입에 성공하였습니다.");
    	return "redirect:/pc/signin";
    }
   
 
}

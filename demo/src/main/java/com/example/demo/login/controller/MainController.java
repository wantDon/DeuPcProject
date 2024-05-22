
package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/pc")
public class MainController {

    public MainController() {
        // TODO Auto-generated constructor stub
    }

    @RequestMapping(value={"*", "/"})
    public String index() {
        return "pc/pc";
    }

    @RequestMapping(value="/login")
    public String indexZero() {
        return "login/login";
    }

    @RequestMapping(value="/smain")
    public String smain(Model model) {
    	HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String ip = req.getHeader("X-FORWARDED-FOR");
		if (ip == null) {
			ip = req.getRemoteAddr();
		}
		model.addAttribute("clientIP", ip);
		
        return "pc/smain";
    }
    
    @RequestMapping(value="/member")
    public String memberMain() {
    	return "pc/memMain";
    }

    @RequestMapping(value="/non-member")
    public String nonmemberMain() {
    	return "pc/nmemMain";
    }

}
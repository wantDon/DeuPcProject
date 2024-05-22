
package com.example.demo.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/pc")
public class MainController {

    public MainController() {
    	
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
    public String smain(Model model, HttpSession session) {
    	HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
    	session.setMaxInactiveInterval(9999 * 60);
    	
		String ip = req.getHeader("X-FORWARDED-FOR");
		String pcnum = "";
		
		if (ip == null) {
			ip = req.getRemoteAddr();
		}
		
		if (ip.equals("127.0.0.1") || ip.equals("113.198.238.105")) {
			pcnum = "01";
		} else if (ip.equals("172.17.103.10")) {
			pcnum = "16";
		}
		
		if (pcnum == null || pcnum.equals("")) {
			return "pc/smain";
		}
		
		model.addAttribute("clientIP", ip);
		model.addAttribute("pcnum", pcnum);
		session.setAttribute("pcnum", pcnum);
		
        return "pc/smain";
    }
    
    @RequestMapping(value="/member")
    public String memberMain(HttpSession session, Model model) {
    	model.addAttribute("pcnum", session.getAttribute("pcnum"));
    	return "pc/memMain";
    }

    @RequestMapping(value="/non-member")
    public String nonmemberMain(HttpSession session, Model model) {
    	model.addAttribute("pcnum", session.getAttribute("pcnum"));
    	return "pc/nmemMain";
    }

}
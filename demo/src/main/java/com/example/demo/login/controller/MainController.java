
package com.example.demo.login.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.example.demo.admin.dto.LoginTimeDTO;
import com.example.demo.admin.service.AdminServiceImple;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/pc")
public class MainController {

	@Autowired
	private AdminServiceImple adminService;
	
	int use = 0;
	
    public MainController() {
    	
    }

    @RequestMapping(value={"/", "", "/pc"})
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
    	
    	List<LoginTimeDTO> ulist = adminService.getAllUse();
    	use = 16 - ulist.size();
    	
		String ip = req.getHeader("X-FORWARDED-FOR");
		String pcnum = "";
		
		if (ip == null) {
			ip = req.getRemoteAddr();
		}
		
		if (ip.equals("127.0.0.1") || ip.equals("113.198.238.105")) {
			pcnum = "01";
		} else if (ip.equals("172.17.98.170")) {
			pcnum = "16";
		} else {
			pcnum = "05";
		}
		
		if (pcnum == null || pcnum.equals("")) {
			return "pc/smain";
		}
		
		model.addAttribute("clientIP", ip);
		model.addAttribute("pcnum", pcnum);
		model.addAttribute("use", use);
		session.setAttribute("pcnum", pcnum);
		
        return "pc/smain";
    }
    
    @RequestMapping(value="/member")
    public String memberMain(HttpSession session, Model model) {
    	model.addAttribute("pcnum", session.getAttribute("pcnum"));
		model.addAttribute("use", use);
    	return "pc/memMain";
    }

    @RequestMapping(value="/non-member")
    public String nonmemberMain(HttpSession session, Model model) {
    	model.addAttribute("pcnum", session.getAttribute("pcnum"));
		model.addAttribute("use", use);
    	return "pc/nmemMain";
    }
    
}
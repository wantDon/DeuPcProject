
package com.example.demo.login.controller;

import com.example.demo.login.dto.LoginDTO;
import com.example.demo.login.service.LoginServiceImple;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@Autowired
    private LoginServiceImple loginService;
    public LoginController(LoginServiceImple loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value="/pc/login")
    public String login(LoginDTO loginDTO, HttpSession session, RedirectAttributes r) {
    	session.setMaxInactiveInterval(9999 * 60);
        boolean result = loginService.login(loginDTO.getId(), loginDTO.getPwd());
    	String pcnum = (String)session.getAttribute("pcnum");

        if (!result) {
            r.addFlashAttribute("error", "아이디 또는 비번호를 확인해주세요.");
            return "redirect:login";
        } else {
            session.setAttribute("loginId", loginDTO.getId());
            return "redirect:pc";
        }
    }

    @GetMapping(value="/pc/login")
    public String chkLogin(HttpSession session) {
        if (session.getAttribute("loginId") != null) {
            return "redirect:pc";
        }
        return "login/login";
    }
    
    @PostMapping(value="/pc/nlogin")
    public String nlogin(LoginDTO loginDTO, HttpSession session, RedirectAttributes r) {
    	String pcnum = (String)session.getAttribute("pcnum");
        loginDTO = loginService.nlogin(loginDTO.getId());
    	System.out.println(loginDTO.getId() + "(" + loginDTO.getPwd() + ") " + pcnum + "번 PC에 접속 시도중");
    	
        if (loginDTO == null || loginDTO.getId().equals("")) {
            r.addFlashAttribute("error", "비회원 로그인 번호를 확인해주세요.");
        	System.out.println("비회원 아이디 오류 " +  pcnum + "번 PC 접속 실패");
            return "redirect:nlogin";
        } else {
    		loginDTO.setId(loginDTO.getId().split("-")[1]);
        	if (loginDTO.getPwd().equals(pcnum) || loginDTO.getPwd().equals("자리이동")) {
	            session.setAttribute("loginId", loginDTO.getId());
	            
	            if (loginDTO.getPwd().equals("자리이동")) {
	            	loginDTO.setPwd(pcnum);
	            	loginService.movePC(loginDTO.getId(), loginDTO.getPwd());
	            }
	            
	        	System.out.println("비회원-" + loginDTO.getId() + " " + pcnum + "번 PC에 접속 성공");
	            return "redirect:pc";
        	} else {
	        	System.out.println("비회원-" + loginDTO.getId() + " 접속 실패");
                r.addFlashAttribute("error", "충전한 좌석이 아닙니다. 관리자에게 문의해주세요.");
                return "redirect:nlogin";
        	}
        }
    }

    @GetMapping(value="/pc/nlogin")
    public String chknLogin(HttpSession session, Model model) {
    	model.addAttribute("pcnum", session.getAttribute("pcnum"));
        if (session.getAttribute("loginId") != null) {
            return "redirect:pc";
        }
        return "login/nlogin";
    }

    @GetMapping(value="/pc/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginId");
        return "redirect:/pc/smain";
    }
}
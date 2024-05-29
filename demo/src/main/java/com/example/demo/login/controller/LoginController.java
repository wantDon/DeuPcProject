
package com.example.demo.login.controller;

import com.example.demo.login.dto.LoginDTO;
import com.example.demo.login.service.LoginServiceImple;
import jakarta.servlet.http.HttpSession;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@Autowired
    private SimpMessagingTemplate messagingTemplate;
	
	@Autowired
    private LoginServiceImple loginService;
    public LoginController(LoginServiceImple loginService) {
        this.loginService = loginService;
    }
    
    @PostMapping(value="/pc/alogin")
    public String alogin(LoginDTO loginDTO, HttpSession session, RedirectAttributes r) {
    	session.setMaxInactiveInterval(9999 * 60);
        boolean result = loginService.alogin(loginDTO.getId(), loginDTO.getPwd());

        if (!result) {
            r.addFlashAttribute("error", "아이디 또는 비번호를 확인해주세요.");
            return "redirect:/pc/alogin";
        } else {
            session.setAttribute("loginId", loginDTO.getId());
        	System.out.println("관리자 로그인");
            return "redirect:/pc/admin";
        }
    }
    
    @GetMapping(value="/pc/alogin")
    public String adminLogin() {
    	return "login/alogin";
    }

    @PostMapping(value="/pc/login")
    @ResponseBody
    public String login(LoginDTO loginDTO, HttpSession session, RedirectAttributes r) {
    	session.setMaxInactiveInterval(9999 * 60);
        loginDTO = loginService.login(loginDTO.getId(), loginDTO.getPwd());
    	String pcnum = (String)session.getAttribute("pcnum");

        if (loginDTO == null || loginDTO.getId() == null) {
//            r.addFlashAttribute("error", "아이디 또는 비번호를 확인해주세요.");
//            return "redirect:login";
        	return "아이디 또는 비밀번호를 확인해주세요.";
        } else if (loginDTO.getTime() <= 0) {
//            r.addFlashAttribute("error", "잔여시간이 없습니다. 시간 충전 후 다시 로그인 해주세요.");
//            return "redirect:login";
        	return "잔여시간이 없습니다. 시간 충전 후 다시 로그인 해주세요.";
        } else {
        	String id = loginDTO.getId();
        	int time = loginDTO.getTime();
        	
        	session.setAttribute("time", time);
        	
        	loginService.useStart(id, pcnum);
            session.setAttribute("loginId", id);
            
            LocalDateTime loginTime = LocalDateTime.now();
            session.setAttribute("loginTime", loginTime);
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedLoginTime = loginTime.format(formatter);
            String formattedLoginTime2 = loginTime.format(formatter2);
            
            messagingTemplate.convertAndSend("/topic/login", id + "/" + pcnum + "/" + formattedLoginTime2 + "/" + time);
        	System.out.println("[" + formattedLoginTime + "] " + id + " " +  pcnum + "번 PC 사용 시작");
            //return "redirect:/pc";
        	return "success";
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
    	session.setMaxInactiveInterval(9999 * 60);
    	String pcnum = (String)session.getAttribute("pcnum");
        loginDTO = loginService.nlogin(loginDTO.getId());
    	
        if (loginDTO == null || loginDTO.getId().equals("")) {
            r.addFlashAttribute("error", "비회원 로그인 번호를 확인해주세요.");
        	System.out.println("비회원 아이디 오류 " +  pcnum + "번 PC 접속 실패");
            return "redirect:nlogin";
        } else {
        	System.out.println(loginDTO.getId() + "(" + loginDTO.getPwd() + ") " + pcnum + "번 PC에 접속 시도중");
    		loginDTO.setId(loginDTO.getId().split("-")[1]);
        	if (loginDTO.getPwd().equals(pcnum) || loginDTO.getPwd().equals("자리이동")) {
        		String id = "비회원-" + loginDTO.getId();
            	int time = loginDTO.getTime();
        		
            	session.setAttribute("time", time);
            	
            	loginService.useStart(id, pcnum);
	            session.setAttribute("loginId", id);
	            
	            LocalDateTime loginTime = LocalDateTime.now();
	            session.setAttribute("loginTime", loginTime);
	            
	            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
	            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	            String formattedLoginTime = loginTime.format(formatter);
	            String formattedLoginTime2 = loginTime.format(formatter2);
	            
	            if (loginDTO.getPwd().equals("자리이동")) {
	            	loginDTO.setPwd(pcnum);
	            	loginService.movePC(id, loginDTO.getPwd());
	            }
	            
	            messagingTemplate.convertAndSend("/topic/login", id + "/" + pcnum + "/" + formattedLoginTime2 + "/" + time);
	        	System.out.println("[" + formattedLoginTime + "] " + id + " " + pcnum + "번 PC에 접속 성공");
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
    	LocalDateTime loginTime = (LocalDateTime)session.getAttribute("loginTime");
    	LocalDateTime logoutTime = LocalDateTime.now();
    	
    	Duration duration = Duration.between(loginTime, logoutTime);
    	long minutes = duration.toMinutes();
    	
    	String id = (String) session.getAttribute("loginId");
    	String pcnum = (String) session.getAttribute("pcnum");
    	
    	LocalDateTime nowTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
        String formattedLoginTime = nowTime.format(formatter);
    	
    	loginService.logout(id, minutes);
    	messagingTemplate.convertAndSend("/topic/login", "logout/" + id + "/" + pcnum);
    	
    	System.out.println("[" + formattedLoginTime + "] " + id + " " + minutes + "분 사용 " + pcnum + "번 PC 종료");
    	
        session.invalidate();
        return "redirect:/pc/smain";
    }
}
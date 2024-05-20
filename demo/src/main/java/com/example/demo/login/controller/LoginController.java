
package com.example.demo.login.controller;

import com.example.demo.login.dto.LoginDTO;
import com.example.demo.login.service.LoginServiceImple;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    private LoginServiceImple loginService;
    @Autowired
    public LoginController(LoginServiceImple loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value="/pc/login")
    public String login(LoginDTO loginDTO, HttpSession session, RedirectAttributes r) {
        boolean result = loginService.login(loginDTO.getId(), loginDTO.getPwd());

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
        boolean result = loginService.nlogin(loginDTO.getId());

        if (!result) {
            r.addFlashAttribute("error", "비회원 로그인 번호를 확인해주세요.");
            return "redirect:nlogin";
        } else {
            session.setAttribute("loginId", loginDTO.getId());
            return "redirect:pc";
        }
    }

    @GetMapping(value="/pc/nlogin")
    public String chknLogin(HttpSession session) {
        if (session.getAttribute("loginId") != null) {
            return "redirect:pc";
        }
        return "login/nlogin";
    }

    @GetMapping(value="/pc/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("loginId");
        return "redirect:login";
    }
}
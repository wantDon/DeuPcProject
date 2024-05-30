package com.example.demo.timePayment.controller;

import com.example.demo.counter.DTO.TimeDTO;
import com.example.demo.counter.DTO.UserDTO;
import com.example.demo.counter.counter.CounterManager;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TimePaymentController {

    private static final Logger log = LoggerFactory.getLogger(TimePaymentController.class);
    private CounterManager counterManager;
    private String name;
    private Model model;

    public TimePaymentController(CounterManager counterManager) {
        this.counterManager = counterManager;
    }

    //사용자의 아이디값으로 회원과 비회원페이지를 나눠서 출력해야함
    @GetMapping(value = "/timePayment/paymentMain")
    public String chkUser(HttpSession session, Model model) {
        if (session.getAttribute("loginId") != null) {
            //회원
            List<TimeDTO> times = counterManager.selectNonUserTime();
            UserDTO userDTO = counterManager.selectUser((String) session.getAttribute("loginId"));
            model.addAttribute("userType", "회원");
            model.addAttribute("userId", userDTO.getId());
            model.addAttribute("times", times);
            model.addAttribute("userTime", userDTO.getTime());
            System.out.println(userDTO.getTime());
            return "timePayment/nonUserPayment";
        }
        //비회원
        model.addAttribute("userType", "비회원");
        List<TimeDTO> times = counterManager.selectUserTime();
        model.addAttribute("times", times);
        return "timePayment/nonUserPayment";
    }

    @GetMapping(value = "/timePayment/paymentMain/payItem")
    public String buyItem(@RequestParam("price") String price, @RequestParam("times") int times, HttpSession session, Model model) {
        System.out.println("TimePaymentController price = " + price + ", times" + times);
        if (session.getAttribute("loginId") != null) {
            //회원일때 1.입력된 정보를 카운터를 이용해 충전한다.2.화면을 이동시켜준다.
            counterManager.addUserTime((String) session.getAttribute("loginId"), times);

            return "/pc/smain";
        } else {
            //비회원은 결제 완료후 바로 이용 시작화면으로
            UserDTO newUserDTO = counterManager.makeNewRandomUser();//신규 유저 생성
            counterManager.addUserTime(newUserDTO.getId(), times);//시간충전
            session.setAttribute("loginId", newUserDTO.getId());//비회원 로그인

        }
        return "/pc/smain";
    }

}





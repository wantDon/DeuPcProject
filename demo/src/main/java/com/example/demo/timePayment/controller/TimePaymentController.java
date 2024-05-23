package com.example.demo.timePayment.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimePaymentController {

    public TimePaymentController(){

    }
    //사용자의 아이디값으로 회원과 비회원페이지를 나눠서 출력해야함
    @GetMapping(value = "/timePayment/paymentMain")
    public String chkUser(HttpSession session){
        if (session.getAttribute("loginId") != null) {
            //회원
            return "/timePayment/userPayment";
        }
        //비회원
        return "/timePayment/nonUserPayment";
    }



}

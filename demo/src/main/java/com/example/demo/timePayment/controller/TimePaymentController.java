package com.example.demo.timePayment.controller;

import com.example.demo.counter.DTO.PaymentDTO;
import com.example.demo.counter.DTO.TimeDTO;
import com.example.demo.counter.DTO.UserDTO;
import com.example.demo.counter.counter.CounterManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
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

    //비회원 사용자가 시간을 충전할려고 할때================================
    @GetMapping(value = "/timePayment/paymentMain_nonUser")
    public String chkNonUser(HttpSession session, Model model, HttpServletRequest request) {
        String id = request.getParameter("id");
        if (id != null) {
            //회원
            List<TimeDTO> times = counterManager.selectUserTime();

            UserDTO userDTO = counterManager.selectUser(id);
            model.addAttribute("userType", "회원");
            model.addAttribute("userId", userDTO.getId());
            model.addAttribute("times", times);
            model.addAttribute("userTime", userDTO.getTime());

            return "timePayment/nonUserPayment";
        }
        //비회원
        model.addAttribute("userType", "비회원");
        List<TimeDTO> times = counterManager.selectNonUserTime();
        model.addAttribute("userId", null);
        model.addAttribute("times", times);
        return "timePayment/nonUserPayment";
    }

    //회원 사용자가 시간을 충전 할려고 할때================================
    @GetMapping(value = "/timePayment/paymentMain_User")
    public String inputUserId(HttpSession session, Model model) {


        return "timePayment/userPayment";
    }

    @PostMapping(value = "/timePayment/paymentMain_User")
    public String chkUser(@RequestParam("id") String id, HttpSession session, Model model) {
        //존재하지 않는 id면 돌려보내야 한다.
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        if (counterManager.selectUser(userDTO.getId()) == null) {
            return "timePayment/userPayment";
        }

        return "redirect:/timePayment/paymentMain_nonUser?id=" + id;
    }

    //결제 완료 페이지=============================
    @GetMapping(value = "/timePayment/paymentMain/payItem")
    public String buyItem(@RequestParam("price") String price, @RequestParam("times") int times, @RequestParam("userId") String userId, HttpSession session, Model model) {
        System.out.println("TimePaymentController price = " + price + ", times" + times + ", userId " + userId);
        if (!userId.equals("undefined")) {
            System.out.println(userId);
            //회원일때 1.입력된 정보를 카운터를 이용해 충전한다.+결제기록을 남긴다. 2.화면을 이동시켜준다.


            PaymentDTO newPaymentDTO = new PaymentDTO();
            newPaymentDTO.setPay_price(Integer.parseInt(price));
            newPaymentDTO.setMethod("결제방법");
            newPaymentDTO.setPay_date(LocalDateTime.now());
            newPaymentDTO.setPay_div(0);
            newPaymentDTO.setPay_state(0);
            newPaymentDTO.setId(userId);

            counterManager.addUserTime(userId, times);
            counterManager.insertPayment(newPaymentDTO);//결제기록 insert

            return "/pc/smain";
        } else {
            //비회원은 결제 완료후 바로 이용 시작화면으로
            UserDTO newUserDTO = counterManager.makeNewRandomUser();//신규 유저 생성
            PaymentDTO newPaymentDTO = new PaymentDTO();


            newPaymentDTO.setPay_price(Integer.parseInt(price));
            newPaymentDTO.setMethod("결제방법");
            newPaymentDTO.setPay_date(LocalDateTime.now());
            newPaymentDTO.setPay_div(0);
            newPaymentDTO.setPay_state(0);
            newPaymentDTO.setId(newUserDTO.getId());

            counterManager.addUserTime(newUserDTO.getId(), times);//시간충전
            counterManager.insertPayment(newPaymentDTO);//결제기록 insert

            session.setAttribute("loginId", newUserDTO.getId());//비회원 로그인

        }
        return "/pc/smain";
    }

}





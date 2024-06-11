package com.example.demo.myprofile.controller;

import com.example.demo.admin.dto.UserSeatDTO;
import com.example.demo.admin.service.AdminServiceImple;
import com.example.demo.myprofile.myprofileDTO.paymentDTO;
import com.example.demo.myprofile.myprofileDTO.userDTO;
import com.example.demo.myprofile.myprofileService.myorderService;
import com.example.demo.myprofile.myprofileService.userService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
public class myprofileController {

    @Autowired
    private userService userService;

    @Autowired
    private myorderService myorderService;
    
    @Autowired
    private AdminServiceImple adminService;

    @GetMapping("/myprofile")
    public String userProfile(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("loginId");
        
		Vector<UserSeatDTO> vlist = adminService.getUserHistory();
		List<UserSeatDTO> filteredList = new ArrayList<>();
		
		if (userId == null) {
			filteredList.addAll(vlist);
		} else {
			for (UserSeatDTO userSeat : vlist) {
				if (userSeat.getId().equals(userId)) {
					filteredList.add(userSeat);
				}
			}
		}
		
		model.addAttribute("seatHistory", filteredList);
        
        if (userId != null) {
            userDTO user = userService.getUserProfile(userId);
            List<paymentDTO> userOrders = myorderService.getUserOrders(userId);

            Map<String, List<paymentDTO>> orderMap = new LinkedHashMap<>();
            for (paymentDTO payment : userOrders) {
                String payDate = payment.getPay_date();
                if (!orderMap.containsKey(payDate)) {
                    orderMap.put(payDate, new ArrayList<>());
                }
                orderMap.get(payDate).add(payment);
            }

            // 역순으로 순서를 바꾸기 위해 LinkedHashMap을 순서대로 역순으로 추가
            Map<String, List<paymentDTO>> reversedOrderMap = new LinkedHashMap<>();
            List<String> keys = new ArrayList<>(orderMap.keySet());
            for (int i = 0; i <= keys.size() - 1; i++) {
                String key = keys.get(i);
                reversedOrderMap.put(key, orderMap.get(key));
            }

            model.addAttribute("userOrders", reversedOrderMap);
            model.addAttribute("user", user);
            return "myprofile/myprofile";
        } else {
            return "redirect:/login";
        }
    }

}

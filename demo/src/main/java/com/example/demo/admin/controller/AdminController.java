package com.example.demo.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.admin.dto.MemberDTO;
import com.example.demo.admin.dto.UserOrdersDTO;
import com.example.demo.admin.dto.UserSeatDTO;
import com.example.demo.admin.service.AdminServiceImple;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping(value="/pc/admin")
public class AdminController {
	
	@Autowired
	private AdminServiceImple adminService;
	
	public AdminController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(value={"*", "/", ""})
	public String AdminMain(HttpSession session) {
//		MemberDTO memberDTO = adminService.getMemberInfo((String)session.getAttribute("loginId"));
//		if (memberDTO == null || memberDTO.getGrade() != 0)
//			return "redirect:/pc/smain";
		return "admin/index";
	}
	
	@GetMapping(value="/order")
	public String AdminCheckOrder() {
		return "admin/admin_order";
	}
	
	@PostMapping(value="/order")
	@ResponseBody
	public Map<String, Object> AdminGetOrder() {
		Map<String, Object> response = new HashMap<>();
		Vector<UserOrdersDTO> vlist = adminService.getOrderList();
		response.put("orderList", vlist);
		return response;
	}
	
	@PostMapping(value="/state/{num}")
	@ResponseBody
	public String UpdateOrderState(@PathVariable int num) {
		adminService.updateUserOrder(num);
		return "success";
	}
	
	@GetMapping("/member")
	public String AdminMember(
		@RequestParam(defaultValue = "0") int page, 
		@RequestParam(defaultValue = "10") int size,
		@RequestParam(required = false) String searchCriteria,
		@RequestParam(required = false) String searchKeyword,
		Model model) {
		
		Pageable pageable = PageRequest.of(page, size);
		Page<MemberDTO> pageResult;
	
		// 검색 조건이 비어있거나 유효하지 않은 경우 모든 사용자 반환
		if (searchCriteria == null || searchCriteria.isEmpty() || searchKeyword == null || searchKeyword.isEmpty()) {
			pageResult = adminService.getAllUser(pageable);
		} else {
			pageResult = adminService.searchUsers(searchCriteria, searchKeyword, pageable);
		}
	
		model.addAttribute("memberList", pageResult.getContent());
		model.addAttribute("currentPage", pageResult.getNumber());
		model.addAttribute("totalPages", pageResult.getTotalPages());
		model.addAttribute("searchCriteria", searchCriteria);
		model.addAttribute("searchKeyword", searchKeyword);
	
		return "admin/member_info";
	}
	
	@PostMapping(value="/resign/{id}")
	@ResponseBody
	public void ResignUser(@PathVariable String id) {
		adminService.resignUser(id);
	}
	
	@PostMapping(value="/grade/{id}/{grade}")
	@ResponseBody
	public void UpdateGrade(@PathVariable String id, @PathVariable int grade) {
		adminService.updateGrade(id, grade);
	}
	
	@GetMapping(value="/seat")
	public String getUserSeatHistory(@RequestParam(required = false) String id, Model model) {
		Vector<UserSeatDTO> vlist = adminService.getUserHistory();
		List<UserSeatDTO> filteredList = new ArrayList<>();
		
		if (id == null) {
			filteredList.addAll(vlist);
		} else {
			for (UserSeatDTO userSeat : vlist) {
				if (userSeat.getId().equals(id)) {
					filteredList.add(userSeat);
				}
			}
		}
		
		model.addAttribute("seatHistory", filteredList);
		
		return "/admin/admin_seat";
	}
	
}
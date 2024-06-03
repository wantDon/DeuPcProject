package com.example.demo.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.admin.dto.LoginTimeDTO;
import com.example.demo.admin.dto.MemberDTO;
import com.example.demo.admin.service.AdminServiceImple;

@RestController
@RequestMapping(value="/pc/admin")
public class UserTimeController {

	@Autowired
	private AdminServiceImple adminService;

	@GetMapping(value="/ulist")
	public List<LoginTimeDTO> getLoginUser() {
		return adminService.getAllUse();
	}
	
	@PostMapping(value="/info/{id}")
	@ResponseBody
	public Map<String, Object> getUserInfo(@PathVariable String id) {
		Map<String, Object> response = new HashMap<>();
		MemberDTO memberDTO = adminService.getMemberInfo(id);
		
		if (memberDTO == null) {
			response.put("error", "검색된 회원이 없거나 오류 입니다. 다시 확인 해주세요.");
		} else {
			response.put("memberInfo", memberDTO);
		}
		
		return response;
	}
	
}

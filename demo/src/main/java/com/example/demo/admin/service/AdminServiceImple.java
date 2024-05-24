package com.example.demo.admin.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.admin.dto.LoginTimeDTO;
import com.example.demo.admin.mapper.AdminMapper;

@Service
public class AdminServiceImple implements AdminService {

	private AdminMapper adminMapper;
	public AdminServiceImple(AdminMapper adminMapper) {
		this.adminMapper = adminMapper;
	}
	
	@Override
	public List getAllUse() {
		List<LoginTimeDTO> ulist = adminMapper.getAllUse();
		return ulist;
	}
	
	@Override
	public LoginTimeDTO getUserUse(String id, LocalDateTime time) {
		Map<String, Object> param = new HashMap<>();
    	param.put("id", id);
    	param.put("time", time);
    	
    	LoginTimeDTO loginTimeDTO = adminMapper.getUseruse(param);
		return loginTimeDTO;
	}
	
}
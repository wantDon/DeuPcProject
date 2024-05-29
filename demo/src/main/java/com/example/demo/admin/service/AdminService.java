package com.example.demo.admin.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Vector;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.demo.admin.dto.LoginTimeDTO;
import com.example.demo.admin.dto.MemberDTO;

public interface AdminService {
	
	public List getAllUse();
	
	public LoginTimeDTO getUserUse(String id, LocalDateTime time);
	
	public MemberDTO getMemberInfo(String id);
	
	public Page<MemberDTO> getAllUser(Pageable pageable);
	
}
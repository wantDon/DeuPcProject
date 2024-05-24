package com.example.demo.admin.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.demo.admin.dto.LoginTimeDTO;

public interface AdminService {
	
	public List getAllUse();
	
	public LoginTimeDTO getUserUse(String id, LocalDateTime time);
	
}
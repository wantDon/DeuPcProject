package com.example.demo.signin.service;

import com.example.demo.signin.dto.SigninDTO;

public interface SigninService {

	public boolean Signin(SigninDTO dto);
	
	public boolean idOverlap(String id);
	
	
}

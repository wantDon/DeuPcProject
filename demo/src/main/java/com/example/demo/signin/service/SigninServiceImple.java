package com.example.demo.signin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.signin.mapper.SigninMapper;

@Service
public class SigninServiceImple implements SigninService {
	
	@Autowired
	private SigninMapper signinMapper;
	public SigninServiceImple(SigninMapper signinMapper) {
		this.signinMapper = signinMapper;
	}
	
	@Override
	public boolean idOver(String id) {
		String ids = signinMapper.idOverlap(id);
		return ids == null;
	}
	
}
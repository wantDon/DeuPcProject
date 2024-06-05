package com.example.demo.signin.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.signin.dto.SigninDTO;
import com.example.demo.signin.mapper.SigninMapper;

@Service
public class SigninServiceImple implements SigninService{
	
	private SigninMapper signinMapper;
	private PasswordEncoder passwordEncoder;
	
	public SigninServiceImple(SigninMapper signinMapper, PasswordEncoder passwordEncoder) {
		this.signinMapper = signinMapper;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	@Override
	public boolean Signin(SigninDTO dto) {
		String Pwd = dto.getPwd();
		String encodePwd = passwordEncoder.encode(Pwd);
		dto.setPwd(encodePwd);
		int result = signinMapper.signin(dto);
		return result > 0;
	}
	
	@Override
	public boolean idOverlap(String id) {
		boolean flag = false;
		SigninDTO idOverlapDTO = signinMapper.idOverlap(id);
		if (idOverlapDTO == null) flag = true;
		return flag;
	}
	
}

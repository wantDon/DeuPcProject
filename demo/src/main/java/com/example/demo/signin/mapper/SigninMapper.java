package com.example.demo.signin.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.signin.dto.SigninDTO;

@Mapper
public interface SigninMapper {

	int signin(SigninDTO dto);
	
	SigninDTO idOverlap(String id);
	
	
}

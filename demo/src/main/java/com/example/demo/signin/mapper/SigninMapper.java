package com.example.demo.signin.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SigninMapper {
	
	public String idOverlap(String id);
	
}
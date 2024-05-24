package com.example.demo.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.admin.dto.LoginTimeDTO;

@Mapper
public interface AdminMapper {

	public List<LoginTimeDTO> getAllUse();
	
	public LoginTimeDTO getUseruse(Map<String, Object> param);
	
}
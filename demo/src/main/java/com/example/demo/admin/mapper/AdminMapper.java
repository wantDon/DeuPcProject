package com.example.demo.admin.mapper;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.admin.dto.LoginTimeDTO;
import com.example.demo.admin.dto.MemberDTO;
import com.example.demo.admin.dto.UserOrdersDTO;
import com.example.demo.admin.dto.UserSeatDTO;

@Mapper
public interface AdminMapper {

	public List<LoginTimeDTO> getAllUse();
	
	public LoginTimeDTO getUseruse(Map<String, Object> param);
	
	public MemberDTO getUserInfo(String id);
	
	public Vector<MemberDTO> getAllUser();
	
	public void resignUser(String id);

	public void updateGrade(Map<String, Object> param);
	
	public Vector<UserOrdersDTO> getOrderList();
	
	public void updateUserOrder(int pay_num);
	
	public Vector<UserSeatDTO> getUserHistory();
	
}
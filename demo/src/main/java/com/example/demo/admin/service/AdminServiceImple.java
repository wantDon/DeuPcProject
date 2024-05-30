package com.example.demo.admin.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.admin.dto.LoginTimeDTO;
import com.example.demo.admin.dto.MemberDTO;
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
	
	@Override
	public MemberDTO getMemberInfo(String id) {
		return adminMapper.getUserInfo(id);
	}
	
	@Override
	public Page<MemberDTO> getAllUser(Pageable pageable) {
		Vector<MemberDTO> allUsers = adminMapper.getAllUser();
		
		List<MemberDTO> formattedUsers = allUsers.stream().map(member -> {
			String birthString = member.getBirth();
			if (birthString != null && !birthString.isEmpty()) {
				try {
					LocalDate birthDate = LocalDate.parse(birthString, DateTimeFormatter.BASIC_ISO_DATE);
					String formattedDate = birthDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
					member.setBirth(formattedDate);
				} catch (DateTimeParseException e) {
					// Handle parse exception if needed
				}
			}
			return member;
		}).collect(Collectors.toList());
		
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), formattedUsers.size());
		List<MemberDTO> pageContent = formattedUsers.subList(start, end);
		
		return new PageImpl<>(pageContent, pageable, formattedUsers.size());
	}
	
	@Override
	public Page<MemberDTO> searchUsers(String searchCriteria, String searchKeyword, Pageable pageable) {
		Vector<MemberDTO> allUsers = adminMapper.getAllUser();
		
		List<MemberDTO> filteredUsers = allUsers.stream().filter(member -> {
			switch (searchCriteria) {
				case "id":
					return member.getId().contains(searchKeyword);
				case "name":
					return member.getName() != null ? member.getName().contains(searchKeyword) : false;
				case "phone":
					return member.getPhone() != null ? member.getPhone().contains(searchKeyword) : false;
				default:
					return false;
			}
		}).collect(Collectors.toList());
		
		List<MemberDTO> formattedUsers = filteredUsers.stream().map(member -> {
			String birthString = member.getBirth();
			if (birthString != null && !birthString.isEmpty()) {
				try {
					LocalDate birthDate = LocalDate.parse(birthString, DateTimeFormatter.BASIC_ISO_DATE);
					String formattedDate = birthDate.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일"));
					member.setBirth(formattedDate);
				} catch (DateTimeParseException e) {
					// Handle parse exception if needed
				}
			}
			return member;
		}).collect(Collectors.toList());
		
		int start = (int) pageable.getOffset();
		int end = Math.min((start + pageable.getPageSize()), formattedUsers.size());
		List<MemberDTO> pageContent = formattedUsers.subList(start, end);
		
		return new PageImpl<>(pageContent, pageable, formattedUsers.size());
	}
	
}
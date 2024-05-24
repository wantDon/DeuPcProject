package com.example.demo.login.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class LoginTime {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userId;
	private LocalDateTime loginTime;
	
	public LoginTime() {
		
	}
	
	public LoginTime(String userId, LocalDateTime loginTime) {
		this.userId = userId;
		this.loginTime = loginTime;
	}
	
}

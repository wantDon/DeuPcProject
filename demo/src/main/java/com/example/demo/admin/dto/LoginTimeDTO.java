package com.example.demo.admin.dto;

import java.time.LocalDateTime;

public class LoginTimeDTO {
	
	private String id;
	private LocalDateTime use_start;
	private String use_seat;
	private int use_time;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getUse_start() {
		return use_start;
	}
	public void setUse_start(LocalDateTime use_start) {
		this.use_start = use_start;
	}
	public String getUse_seat() {
		return use_seat;
	}
	public void setUse_seat(String use_seat) {
		this.use_seat = use_seat;
	}
	public int getUse_time() {
		return use_time;
	}
	public void setUse_time(int use_time) {
		this.use_time = use_time;
	}
	
}
package com.example.demo.login.dto;

public class LoginDTO {

    private String id;
    private String pwd;
    private int time;

	public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPwd() {
        return pwd;
    }
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}

}

package com.example.demo.login.service;

import com.example.demo.login.dto.LoginDTO;

public interface LoginService {

    public boolean login(String id, String pwd);
    
    public LoginDTO nlogin(String id);

    public void movePC(String id, String pwd);
    
}
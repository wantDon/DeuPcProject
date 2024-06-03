package com.example.demo.login.service;

import java.util.List;

import com.example.demo.login.dto.LoginDTO;

public interface LoginService {

    public LoginDTO login(String id, String pwd);
    
    public LoginDTO nlogin(String id);
    
    public boolean alogin(String id, String pwd); 

    public void movePC(String id, String pwd);
    
    public void useStart(String id, String pcnum);
    
    public void logout(String id, long time);
    
}
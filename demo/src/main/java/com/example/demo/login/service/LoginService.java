package com.example.demo.login.service;

import com.example.demo.login.dto.LoginDTO;

public interface LoginService {

    public LoginDTO login(String id, String pwd);
    
    public LoginDTO nlogin(String id);
    
    public boolean alogin(String id, String pwd); 

    public void movePC(String id, String pwd);
    
    public void useStart(String id, String pcnum);
    
    public void logout(String id, long time);
    
    public void moveUser(String id);
    
    public int moveCheck(String id);
    
    public void moveUse(String id, String pcnum);
    
    public String getMovetime(String id);
    
}
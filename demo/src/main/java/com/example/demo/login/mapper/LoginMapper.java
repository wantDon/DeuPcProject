package com.example.demo.login.mapper;

import com.example.demo.login.dto.LoginDTO;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    LoginDTO login(String id);
    
    LoginDTO nlogin(String id);
    
    LoginDTO alogin(String id);

    public void movePC(String id, String pwd);
    
    public void useStart(Map<String, Object> param);
    
    public void logout(Map<String, Object> param);
    
    public void logout2(Map<String, Object> param);
    
    public void logout3(String id);
    
    public void moveUse(Map<String, Object> param);
    
    public void moveUser(String id);
    
    public int moveCheck(String id);
    
    public String getMovetime(String id);
    
    public int getUserTime(String id);
    
}

package com.example.demo.login.service;

import com.example.demo.login.dto.LoginDTO;
import com.example.demo.login.mapper.LoginMapper;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImple implements LoginService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
    private LoginMapper loginMapper;

    public LoginServiceImple(LoginMapper loginMapper){
        this.loginMapper = loginMapper;
    }
    
    @Override
    public int getUserTime(String id) {
    	return loginMapper.getUserTime(id);
    }
    
    @Override
    public String getMovetime(String id) {
    	return loginMapper.getMovetime(id);
    }
    
    @Override
    public void moveUse(String id, String pcnum) {
    	Map<String, Object> param = new HashMap<>();
    	param.put("id", id);
    	param.put("pcnum", pcnum);
    	
    	loginMapper.moveUse(param);
    }

    @Override
    public int moveCheck(String id) {
    	return loginMapper.moveCheck(id);
    }
    
    @Override
    public void moveUser(String id) {
    	loginMapper.moveUser(id);
    }
    
    @Override
    public LoginDTO login(String id, String pwd) {
    	LoginDTO dto = loginMapper.login(id);
    	
    	if (dto == null) return null;
    	
    	if (passwordEncoder.matches(pwd, dto.getPwd())) {
    		return dto;
    	} else {
    		return null;
    	}
    }
    
    @Override
    public LoginDTO nlogin(String id) {
    	id = "비회원-" + id;
        LoginDTO loginDTO = loginMapper.nlogin(id);
        return loginDTO;
    }
    
    @Override
    public boolean alogin(String id, String pwd) {
    	LoginDTO dto = loginMapper.alogin(id);
    	
    	if (dto == null) return false;
    	
    	if (passwordEncoder.matches(pwd, dto.getPwd())) {
    		return true;
    	} else {
    		return false;
    	}
    }
    
    @Override
    public void movePC(String id, String pwd) {
    	loginMapper.movePC(id, pwd);
    }

    @Override
    public void useStart(String id, String pcnum) {
    	Map<String, Object> param = new HashMap<>();
    	param.put("id", id);
    	param.put("pcnum", pcnum);
    	
    	loginMapper.useStart(param);
    }
    
    @Override
    public void logout(String id, long time) {
    	Map<String, Object> param = new HashMap<>();
    	param.put("id", id);
    	param.put("time", time);
    	
    	loginMapper.logout(param);
    	loginMapper.logout2(param);
    }
    
}


package com.example.demo.login.service;

import com.example.demo.login.dto.LoginDTO;
import com.example.demo.login.mapper.LoginMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImple implements LoginService {

    private LoginMapper loginMapper;
    public LoginServiceImple(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    @Override
    public boolean login(String id, String pwd) {
        LoginDTO loginDTO = loginMapper.login(id, pwd);
        return loginDTO != null;
    }

}


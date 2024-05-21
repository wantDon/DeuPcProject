package com.example.demo.login.service;

import com.example.demo.counter.counter.CounterManager;
import com.example.demo.login.dto.LoginDTO;
import com.example.demo.login.mapper.LoginMapper;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImple implements LoginService {

    private LoginMapper loginMapper;
    private CounterManager counterManager;

    public LoginServiceImple(LoginMapper loginMapper,CounterManager counterManager){
        this.loginMapper = loginMapper;
        this.counterManager = counterManager;
    }

    @Override
    public boolean login(String id, String pwd) {
        LoginDTO loginDTO = loginMapper.login(id, pwd);
        //임의실험으로 이곳에서 회원 시간을 10식 추가해 보자 로그인 할때마다.
        counterManager.addUserTime(id,5);
        return loginDTO != null;
    }

}


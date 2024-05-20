package com.example.demo.login.mapper;

import com.example.demo.login.dto.LoginDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LoginMapper {

    LoginDTO login(String id, String pwd);

}

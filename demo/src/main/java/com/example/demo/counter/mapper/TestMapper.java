package com.example.demo.counter.mapper;

import com.example.demo.counter.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestMapper {

    UserDTO selectUser(String Id);
    void updateUser(UserDTO userDTO);

}

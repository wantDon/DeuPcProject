package com.example.demo.counter.mapper;

import com.example.demo.counter.DTO.TimeDTO;
import com.example.demo.counter.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    //user
    UserDTO selectUser(String Id);
    void updateUser(UserDTO userDTO);

    //time
    List<TimeDTO> selectAllTime();
}

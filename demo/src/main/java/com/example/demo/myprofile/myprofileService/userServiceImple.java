package com.example.demo.myprofile.myprofileService;

import com.example.demo.myprofile.myprofileDTO.userDTO;
import com.example.demo.myprofile.myprofileMapper.userMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userServiceImple implements userService {

    private final userMapper userMapper;

    @Autowired
    public userServiceImple(userMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public userDTO getUserProfile(String userId) {
        return userMapper.getUserById(userId);
    }
}

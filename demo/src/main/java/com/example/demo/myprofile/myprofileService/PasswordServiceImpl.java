package com.example.demo.myprofile.myprofileService;

import com.example.demo.myprofile.myprofileMapper.PassswordMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
@Service
public class PasswordServiceImpl implements passwordService {
    @Autowired
    private PassswordMapper passswordMapper;

    @Autowired
    private PasswordEncoder myProfilePasswordEncoder;

    @Override
    @Transactional
    public boolean updatePassword(String userId, String newPassword) {
        String encodedPassword = myProfilePasswordEncoder.encode(newPassword);
        int rowsAffected = passswordMapper.updatePassword(userId, encodedPassword);
        return rowsAffected > 0;
    }
}


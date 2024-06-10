package com.example.demo.myprofile.myprofileService;

import com.example.demo.myprofile.myprofileDTO.paymentDTO;
import com.example.demo.myprofile.myprofileMapper.myorderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class myorderServiceImple implements myorderService {

    @Autowired
    private myorderMapper myorderMapper;

    @Override
    public List<paymentDTO> getUserOrders(String userId) {
        return myorderMapper.getUserOrders(userId);
    }
}

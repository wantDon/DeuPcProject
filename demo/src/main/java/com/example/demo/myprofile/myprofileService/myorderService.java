package com.example.demo.myprofile.myprofileService;

import com.example.demo.myprofile.myprofileDTO.paymentDTO;

import java.util.List;

public interface myorderService {
    List<paymentDTO> getUserOrders(String userId);
}

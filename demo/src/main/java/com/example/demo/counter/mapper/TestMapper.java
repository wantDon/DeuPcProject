package com.example.demo.counter.mapper;

import com.example.demo.counter.DTO.PaymentDTO;
import com.example.demo.counter.DTO.TimeDTO;
import com.example.demo.counter.DTO.UserDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    //user
    void insertUser(UserDTO userDTO);

    UserDTO selectUser(String Id);

    void updateUser(UserDTO userDTO);

    //time
    List<TimeDTO> selectNonUserTime();

    List<TimeDTO> selectUserTime();

    //payment
    void insertPayment(PaymentDTO paymentDTO);

    PaymentDTO selectPayment(int pay_num);

    void updatePayment(PaymentDTO paymentDTO);

    void deletePayment(PaymentDTO paymentDTO);
}

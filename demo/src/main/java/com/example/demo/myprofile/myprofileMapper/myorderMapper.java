package com.example.demo.myprofile.myprofileMapper;

import com.example.demo.myprofile.myprofileDTO.paymentDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface myorderMapper {
    @Select("SELECT p.*, o.*, f.* FROM payment p JOIN `order` o ON p.pay_num = o.pay_num JOIN food f ON o.food_num = f.food_num WHERE p.id = #{userId} order by p.pay_num desc;")
    List<paymentDTO> getUserOrders(@Param("userId") String userId);
}

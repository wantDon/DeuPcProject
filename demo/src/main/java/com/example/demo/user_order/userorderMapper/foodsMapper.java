package com.example.demo.user_order.userorderMapper;

import com.example.demo.user_order.userorderDTO.foodDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface foodsMapper {
    @Select("SELECT * FROM food")
    List<foodDTO> getAllfood();
}
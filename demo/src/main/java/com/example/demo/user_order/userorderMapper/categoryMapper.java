package com.example.demo.user_order.userorderMapper;

import com.example.demo.user_order.userorderDTO.categoryDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface categoryMapper {
    @Select("SELECT * FROM category")
    List<categoryDTO> getAllCategories();
}
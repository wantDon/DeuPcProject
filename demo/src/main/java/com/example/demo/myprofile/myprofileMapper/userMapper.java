package com.example.demo.myprofile.myprofileMapper;

import com.example.demo.myprofile.myprofileDTO.userDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface userMapper {

    @Select("SELECT * FROM user WHERE id = #{userId}")
    userDTO getUserById(@Param("userId") String userId);
}

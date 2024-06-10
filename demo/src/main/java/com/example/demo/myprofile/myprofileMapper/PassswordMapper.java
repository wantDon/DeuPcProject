package com.example.demo.myprofile.myprofileMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
@Mapper
public interface PassswordMapper {
    @Update("UPDATE user SET pwd = #{newPassword} WHERE id = #{userId}")
    int updatePassword(@Param("userId") String id, @Param("newPassword") String pwd);
}

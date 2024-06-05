package com.example.demo.sell.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.sell.dto.SellDTO;

@Mapper
public interface SellMapper {
    List<SellDTO> getAllSells();

    List<SellDTO> getSellByPage(@Param("offset") int offset, @Param("pageSize") int pageSize);
    
    int getTotalSellCount();
    
    List<SellDTO> getFilteredSellByPage(@Param("startDate") String startDate, @Param("endDate") String endDate, @Param("offset") int offset, @Param("pageSize") int pageSize);
    
    int getTotalFilteredSellCount(@Param("startDate") String startDate, @Param("endDate") String endDate);

    List<SellDTO> getFilteredSellBySearch(@Param("searchTerm") String searchTerm, @Param("offset") int offset, @Param("pageSize") int pageSize);

    int getTotalFilteredSellCountBySearch(@Param("searchTerm") String searchTerm);
}

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

    // 전체 상품 목록과 각 상품의 판매 내역을 가져오는 쿼리
    List<SellDTO> getAllProductsAndSales(@Param("offset") int offset, @Param("pageSize") int pageSize);
    
    int getTotalProductsCount();

	int getTotalSellsCount();
}

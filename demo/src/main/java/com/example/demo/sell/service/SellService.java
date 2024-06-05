package com.example.demo.sell.service;

import java.util.List;

import com.example.demo.sell.dto.SellDTO;

public interface SellService {
	
	List<SellDTO> getListSell();
    
    int getTotalSellCount();
    
    List<SellDTO> getListSellByPage(int page, int pageSize);
    
    // 추가: 필터링된 판매 내역을 가져오는 메서드
    List<SellDTO> getListFilteredSellByPage(String startDate, String endDate, int page, int pageSize);
    
    // 추가: 필터링된 판매 내역 개수를 가져오는 메서드
    int getTotalFilteredSellCount(String startDate, String endDate);
    
    List<SellDTO> getListSellBySearch(String searchTerm, int page, int pageSize);
    
    int getTotalFilteredSellCountBySearch(String searchTerm);
}

package com.example.demo.sell.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.sell.dto.SellDTO;
import com.example.demo.sell.mapper.SellMapper;

@Service
public class SellServiceImple implements SellService {

	private final SellMapper sellMapper;
	
	@Autowired
	public SellServiceImple(SellMapper sellMapper) {
		this.sellMapper = sellMapper;
	}
	
	@Override
	public List getListSell() {
		return sellMapper.getAllSells();
	}
	
	@Override
	public int getTotalSellCount() {
        return sellMapper.getTotalSellCount();
    }

	@Override
    public List<SellDTO> getListSellByPage(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return sellMapper.getSellByPage(offset, pageSize);
    }
	
	@Override
    public int getTotalFilteredSellCount(String startDate, String endDate) {
        if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
            return sellMapper.getTotalFilteredSellCount(startDate, endDate);
        } else {
            return getTotalSellCount();
        }
    }

	@Override
	public List<SellDTO> getListFilteredSellByPage(String startDate, String endDate, int page, int pageSize) {
	    if (startDate != null && !startDate.isEmpty() && endDate != null && !endDate.isEmpty()) {
	        int offset = (page - 1) * pageSize;
	        // 시작일과 종료일을 datetime으로 변환
	        startDate = formatDateToDateTime(startDate);
	        endDate = formatDateToDateTime(endDate);
	        return sellMapper.getFilteredSellByPage(startDate, endDate, offset, pageSize);
	    } else {
	        return getListSellByPage(page, pageSize);
	    }
	}

	// 날짜를 datetime으로 변환하는 메서드
	private String formatDateToDateTime(String date) {
	    return date + " 00:00:00"; // 시간 정보를 추가하여 datetime 형식으로 반환
	}

    
    @Override
    public List<SellDTO> getListSellBySearch(String searchTerm, int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return sellMapper.getFilteredSellBySearch(searchTerm, offset, pageSize);
    }

    @Override
    public int getTotalFilteredSellCountBySearch(String searchTerm) {
        return sellMapper.getTotalFilteredSellCountBySearch(searchTerm);
    }
}

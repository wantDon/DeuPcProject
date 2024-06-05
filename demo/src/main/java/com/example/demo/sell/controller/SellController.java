package com.example.demo.sell.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.notice.dto.NoticeDTO;
import com.example.demo.sell.dto.SellDTO;
import com.example.demo.sell.service.SellServiceImple;

@Controller
@RequestMapping("/sell")
public class SellController {

    private final SellServiceImple sellService;

    @Autowired
    public SellController(SellServiceImple sellService) {
        this.sellService = sellService;
    }
    
    // 판매 내역 페이지 요청 처리
    @RequestMapping(value={"", "*"})
    public String sell(Model model, 
                       @RequestParam(defaultValue = "1") int page, 
                       @RequestParam(defaultValue = "") String sword) {
        int pageSize = 10;
        int totalSells;
        List<SellDTO> sellList;

        if (sword.isEmpty()) {
            totalSells = sellService.getTotalSellCount();
            sellList = sellService.getListSellByPage(page, pageSize);
        } else {
            totalSells = sellService.getTotalFilteredSellCountBySearch(sword);
            sellList = sellService.getListSellBySearch(sword, page, pageSize);
        }

        int totalPages = (int) Math.ceil((double) totalSells / pageSize);

        model.addAttribute("sellList", sellList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("sword", sword);
        model.addAttribute("pageName", "판매 현황");

        return "sell/sell";
    }

    // 판매 내역 페이지 요청 처리
    @RequestMapping("/sell_search")
    public String sellSearch(Model model, 
                             @RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "") String startDate,
                             @RequestParam(defaultValue = "") String endDate) {
        int pageSize = 10;
        int totalSells = sellService.getTotalFilteredSellCount(startDate, endDate);
        int totalPages = (int) Math.ceil((double) totalSells / pageSize);

        if (page < 1 || page > totalPages) {
            page = 1; // 페이지가 범위를 벗어나면 첫 페이지로 설정
        }

        List<SellDTO> sellList = sellService.getListFilteredSellByPage(startDate, endDate, page, pageSize);

        model.addAttribute("sellList", sellList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        model.addAttribute("pageName", "판매 내역");

        return "sell/sell_search";
    }

    // 검색어에 따른 판매 내역 페이지 요청 처리
    @RequestMapping("/search")
    public String searchSell(Model model, 
                             @RequestParam(defaultValue = "1") int page,
                             @RequestParam(defaultValue = "") String searchTerm) {
        int pageSize = 10;
        int totalSells = sellService.getTotalFilteredSellCountBySearch(searchTerm);
        int totalPages = (int) Math.ceil((double) totalSells / pageSize);

        if (page < 1 || page > totalPages) {
            page = 1; // 페이지가 범위를 벗어나면 첫 페이지로 설정
        }

        List<SellDTO> sellList = sellService.getListSellBySearch(searchTerm, page, pageSize);

        model.addAttribute("sellList", sellList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("searchTerm", searchTerm);
        model.addAttribute("pageName", "판매 내역 검색 결과");

        return "sell/search";
    }


}
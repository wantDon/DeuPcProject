package com.example.demo.priceListManagement.controller;

import com.example.demo.counter.DTO.TimeDTO;
import com.example.demo.counter.counter.CounterManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PriceListManagementController {

    @Autowired
    CounterManager counterManager;

    public PriceListManagementController() {

    }

    @RequestMapping(value = "/priceListManagement/priceListManagement")
    public String mainPriceListManagement(Model model) {
        //요금정보 뒤져서 보내준다.
        List<TimeDTO> timeList = counterManager.selectAllTime();

        model.addAttribute("timeList", timeList);
        return "priceListManagement/priceListManagement";
    }

    @RequestMapping(value = "/priceListManagement/priceListManagement/newTimeMenu")
    public String newTimeMenu(Model model) {
        //신규 요금표를 생성해준다.
        TimeDTO timeDTO = new TimeDTO();
        timeDTO.setTime_div(0);
        timeDTO.setTime_use(10);
        timeDTO.setTime_price(100);

        counterManager.insertTime(timeDTO);

        return "redirect:/priceListManagement/priceListManagement";
    }

    @RequestMapping(value = "/priceListManagement/priceListManagement/refixTimeMenu")
    public String updateTimeMenu(Model model, @RequestParam("timeNum") String timeNum,
                                 @RequestParam("time_div") String time_div,
                                 @RequestParam("time_use") String time_use,
                                 @RequestParam("time_price") String time_price) {
        //수정 요금표를 생성해준다.
        TimeDTO timeDTO = new TimeDTO();
        timeDTO.setTime_num(Integer.parseInt(timeNum));
        timeDTO.setTime_div(Integer.parseInt(time_div));
        timeDTO.setTime_use(Integer.parseInt(time_use));
        timeDTO.setTime_price(Integer.parseInt(time_price));
        
        counterManager.updateTime(timeDTO);

        return "redirect:/priceListManagement/priceListManagement";
    }

}

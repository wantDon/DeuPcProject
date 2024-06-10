package com.example.demo.priceListManagement.service;

import com.example.demo.priceListManagement.mapper.PriceListManagementMapper;
import org.springframework.stereotype.Service;

@Service
public class PriceListManagementServicceImple implements PriceListManagementService {

    PriceListManagementMapper priceListManagementMapper;

    PriceListManagementServicceImple(PriceListManagementMapper priceListManagementMapper) {
        this.priceListManagementMapper = priceListManagementMapper;
    }

}








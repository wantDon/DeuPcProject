package com.example.demo.inventory.controller;

import com.example.demo.inventory.invenDTO.InvenDTO;
import com.example.demo.inventory.invenService.InvenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class InventoryController {

    private final InvenService inventoryService;

    @Autowired
    public InventoryController(InvenService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @GetMapping("/inventory")
    public String showInventory(Model model) {
        List<InvenDTO> inventoryList = inventoryService.getAllInventory();
        model.addAttribute("inventoryList", inventoryList);
        return "inventory/inventory"; // View 이름 반환
    }




}

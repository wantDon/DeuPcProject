package com.example.demo.inventory.controller;

import com.example.demo.inventory.invenDTO.InvenDTO;
import com.example.demo.inventory.invenService.InvenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InventoryUpdateController {

    private final InvenService inventoryService;
    public InventoryUpdateController(InvenService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping("/inventory/update")
    public boolean updateFoodItem(InvenDTO invenDTO) {
        return inventoryService.updateFoodItem(invenDTO);
    }
}

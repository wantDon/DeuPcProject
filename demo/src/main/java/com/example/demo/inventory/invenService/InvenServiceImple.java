package com.example.demo.inventory.invenService;

import com.example.demo.inventory.invenDTO.InvenDTO;
import com.example.demo.inventory.invenMapper.InventoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvenServiceImple implements InvenService {

    private final InventoryMapper inventoryMapper;

    @Autowired
    public InvenServiceImple(InventoryMapper inventoryMapper) {
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    public List<InvenDTO> getAllInventory() {
        return inventoryMapper.getAllInventory();
    }

    @Override
    public boolean updateFoodItem(InvenDTO invenDTO) {
        System.out.println("Updating food item with data: " + invenDTO); // 디버깅 출력
        boolean updateResult = inventoryMapper.updateFoodItem(invenDTO);
        System.out.println("Update result: " + updateResult); // 디버깅 출력
        return updateResult;
    }
}

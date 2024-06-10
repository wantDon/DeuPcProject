package com.example.demo.inventory.invenService;

import com.example.demo.inventory.invenDTO.InvenDTO;
import java.util.List;

public interface InvenService {
    List<InvenDTO> getAllInventory();
    boolean updateFoodItem(InvenDTO invenDTO);
}

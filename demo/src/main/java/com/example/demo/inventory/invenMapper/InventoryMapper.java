    package com.example.demo.inventory.invenMapper;

    import com.example.demo.inventory.invenDTO.InvenDTO;
    import org.apache.ibatis.annotations.Mapper;
    import org.apache.ibatis.annotations.Select;
    import org.apache.ibatis.annotations.Update;

    import java.util.List;

    @Mapper
    public interface InventoryMapper {
        @Select("SELECT * FROM food")
        List<InvenDTO> getAllInventory();

        @Update("UPDATE food SET count = #{count} WHERE food_num = #{food_num}")
        boolean updateFoodItem(InvenDTO invenDTO);

    }

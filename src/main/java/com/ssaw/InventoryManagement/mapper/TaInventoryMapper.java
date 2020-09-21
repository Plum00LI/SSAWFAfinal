package com.ssaw.InventoryManagement.mapper;

import com.ssaw.InventoryManagement.entity.TaInventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * ClassName:    TaInventoryMapper
 * Package:    com.ssaw.InventoryManagement.mapper
 * Description:TA库存Mapper类
 * Version:   1.0
 * Datetime:    2020/9/1   9:21
 * Author:   SYT
 */
@Mapper
public interface TaInventoryMapper {
    /**
     * 分页查询ta库存方法
     * @param map
     */
    public void selectTaInventory(Map map);

    /**
     * 增加
     * @param taInventory
     * @return
     */
    public int insertTaInventory(TaInventory taInventory);

    /**
     * 修改ta库存信息
     * @param taInventory
     * @return
     */
    public int updateTaInventory(TaInventory taInventory);

    /**
     * 删除
     * @param taInventoryId
     * @return
     */
    public int deleteTaInventory(List taInventoryId);

    /**
     * 根据日期删除
     * @param dateTime
     */
    public void deleteTaInventoryDate(String dateTime,String fundId);
}

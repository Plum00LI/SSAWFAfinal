package com.ssaw.InventoryManagement.service;

import com.ssaw.InventoryManagement.entity.TaInventory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ClassName:    TaInventoryService
 * Package:    com.ssaw.InventoryManagement.service
 * Description:
 * Version:   1.0
 * Datetime:    2020/9/1   9:31
 * Author:   SYT
 */
@Service

public interface TaInventoryService {
    /**
     * 分页查询
     * @param pageSize  每页条数
     * @param page      页数
     * @return
     */
    public Map<String,Object> selectTaInventory(String pageSize,String page,String dateTime);

    /**
     * 增加
     * @param taInventory
     * @return
     */
    public int insertTaInventory(TaInventory taInventory);

    /**
     * 修改
     * @param taInventory
     * @return
     */
    public int updateTaInventory(TaInventory taInventory);

    /**
     * 删除
     * @param taInventoryId
     * @return
     */
    public int deleteTaInventory(String taInventoryId);


}

package com.ssaw.InventoryManagement.service;

import com.ssaw.InventoryManagement.entity.CashInventory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * ClassName:    CashInventoryService
 * Package:    com.ssaw.InventoryManagement.service
 * Description:
 * Version:   1.0
 * Datetime:    2020/9/1   9:29
 * Author:   SYT
 */
@Service

public interface CashInventoryService {

    /**
     * 查询所有现金库存的服务类接口方法-待实现
     * @param pageSize 每页条数
     * @param page 页数
     * @return
     */
    public Map<String,Object> selectCashInventory(String pageSize,String page,String accountId,String dateTime,String fundId);

    /**
     * 增加
     * @param cashInventory
     * @return
     */
    public int insertCashInventory(CashInventory cashInventory);

    /**
     * 修改
     * @param cashInventory
     * @return
     */
    public int updateCashInventory(CashInventory cashInventory);

    /**
     * 批量删除
     * @param cashInventoryId
     * @return
     */
    public int deleteCashInventory(String cashInventoryId);


}

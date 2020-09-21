package com.ssaw.InventoryManagement.mapper;

import com.ssaw.InventoryManagement.entity.CashInventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * ClassName:    CashInventoryMapper
 * Package:    com.ssaw.InventoryManagement.mapper
 * Description:     现金库存增删改查
 * Version:   1.0
 * Datetime:    2020/9/1   9:26
 * Author:   SYT
 */
@Mapper
public interface CashInventoryMapper {
    //分页查询所有现金库存方法
    public void selectCashInventory(Map map);

    /**
     * 增加现金库存
     * @param cashInventory 现金库存实体对象
     * @return
     */
    public int insertCashInventory(CashInventory cashInventory);

    /**
     * 修改现金库存信息
     * @param cashInventory
     * @return
     */
    public int updateCashInventory(CashInventory cashInventory);

    /**
     * 批量删除
     * @param cashInventoryId id
     * @return
     */
    public int deleteCashInventory(List cashInventoryId);

    /**
     * 根据日期删除
     * @param dateTime
     */
    public void deleteCashInventoryDate(String accountId,String dateTime,String fundId);
}
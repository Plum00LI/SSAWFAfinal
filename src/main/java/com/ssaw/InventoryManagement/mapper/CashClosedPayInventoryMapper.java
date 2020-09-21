package com.ssaw.InventoryManagement.mapper;

import com.ssaw.InventoryManagement.entity.CashClosedPayInventory;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @program:现金应收应付库存模块
 * @Description:Dao层接口
 * @author:孙浩
 * @create:2020-09-01
 */
@Mapper
public interface CashClosedPayInventoryMapper {
    public int insertCashClosedPayInventory(CashClosedPayInventory cashClosedPayInventory);
    public int deleteCashClosedPayInventory(List cashClosedPayInventoryId);
    public int updateCashClosedPayInventory(CashClosedPayInventory cashClosedPayInventory);
    public void selectCashClosedPayInventory(Map map);
    public void deleteCashClosedPayInventoryDate(String accountId,String dateTime,String fundId);
}

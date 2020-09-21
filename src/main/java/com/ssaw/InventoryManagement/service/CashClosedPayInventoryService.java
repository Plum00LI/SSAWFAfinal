package com.ssaw.InventoryManagement.service;

import com.ssaw.InventoryManagement.entity.CashClosedPayInventory;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @program:现金应收应付库存模块
 * @Description:Biz层接口
 * @author:孙浩
 * @create:2020-09-01
 */
@Service
public interface CashClosedPayInventoryService {
    public int insertCashClosedPayInventory(CashClosedPayInventory cashClosedPayInventory);
    public int deleteCashClosedPayInventory(String cashClosedPayInventoryId);
    public int updateCashClosedPayInventory(CashClosedPayInventory cashClosedPayInventory);
    public Map<String,Object> selectCashClosedPayInventory(String pageSize,String page,String businessType,String businessDate);
}

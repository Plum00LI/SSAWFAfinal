package com.ssaw.InventoryManagement.controller;

import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import com.ssaw.InventoryManagement.entity.CashClosedPayInventory;
import com.ssaw.InventoryManagement.service.CashClosedPayInventoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:现金应收应付库存模块
 * @Description:控制类
 * @author:孙浩
 * @create:2020-09-01
 */
@RestController
public class CashClosedPayInventoryController {
    @Resource
    CashClosedPayInventoryService cashClosedPayInventoryService;

    @Resource
    DbUtil dbUtil;

    @RequestMapping("insertCashClosedPayInventory")
    public int insertCashClosedPayInventory(CashClosedPayInventory cashClosedPayInventory){
        System.out.println(cashClosedPayInventory);
        cashClosedPayInventory.setCashClosedPayInventoryId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCPI));
        int j = cashClosedPayInventoryService.insertCashClosedPayInventory(cashClosedPayInventory);
        return j;
    }

    @RequestMapping("deleteCashClosedPayInventory")
    public int deleteCashClosedPayInventory(String cashClosedPayInventoryId){
        int j = cashClosedPayInventoryService.deleteCashClosedPayInventory(cashClosedPayInventoryId);
        return j;
    }

    @RequestMapping("updateCashClosedPayInventory")
    public int updateCashClosedPayInventory(CashClosedPayInventory cashClosedPayInventory){
        int j = cashClosedPayInventoryService.updateCashClosedPayInventory(cashClosedPayInventory);
        return j;
    }

    @RequestMapping("selectCashClosedPayInventory")
    public Map<String,Object> selectCashClosedPayInventory(String page,String limit,String businessType,String businessDate){
        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map = cashClosedPayInventoryService.selectCashClosedPayInventory(limit,page,businessType,businessDate);
        List<CashClosedPayInventory> cashClosedPayInventoryList = (List<CashClosedPayInventory>) map.get("cashClosedPayInventoryList");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> cashClosedPayInventoryMap = new HashMap<>();
        cashClosedPayInventoryMap.put("code",0);
        cashClosedPayInventoryMap.put("msg","");
        cashClosedPayInventoryMap.put("count",count);
        cashClosedPayInventoryMap.put("data",cashClosedPayInventoryList);
        //返回数据
        return cashClosedPayInventoryMap;
    }
}

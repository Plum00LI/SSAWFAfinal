package com.ssaw.InventoryManagement.controller;

import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.OperationType;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import com.ssaw.InventoryManagement.entity.CashInventory;
import com.ssaw.InventoryManagement.service.CashInventoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:    CashInventoryController
 * Package:    com.ssaw.InventoryManagement.controller
 * Description:
 * Datetime:    2020/9/1   9:45
 * Author:   SYT
 */
@RestController
@RequestMapping("/cashInventory")
public class CashInventoryController {
    @Resource
    CashInventoryService cashInventoryService;

    @Resource
    DbUtil dbUtil;

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/select")
    @OperLog(message = "分页查询现金库存",operation = OperationType.QUERY)
    public Map<String,Object> selectCashInventory(String page, String limit,String accountId,String dateTime,String fundId){
        //调用Service层执行查询，接收返回结果集Map
        System.out.println("现金库存的fundId"+fundId);
        Map<String, Object> map =  cashInventoryService.selectCashInventory(limit,page,accountId,dateTime,fundId);

        //从结果集中拿出结果
        List<CashInventory> cashInventoryList = (List<CashInventory>) map.get("cashInventory");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        //code
        json.put("code",0);
        //msg
        json.put("msg","");
        //count
        json.put("count",count);
        //data
        json.put("data",cashInventoryList);
        //返回数据
        return json;
    }

    /**
     * 新增现金库存信息
     * @param cashInventory
     * @return
     */
    @RequestMapping("/insert")
    @OperLog(message = "新增现金库存信息",operation = OperationType.ADD)
    public int insertCashInventory(CashInventory cashInventory){
        //现金库存Id
        cashInventory.setCashInventoryId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.CI));

        cashInventory.setSecuritiesNum(0);
        //是否从其他系统导入的期初数据  0：不是  1：是
        cashInventory.setSecurityPeriodFlag(1);
        System.out.println(cashInventory);
        int i=cashInventoryService.insertCashInventory(cashInventory);
        return i;
    }

    /**
     * 修改库存信息
     * @param cashInventory  现金库存实体类对象
     * @return
     */
    @RequestMapping("/update")
    @OperLog(message = "修改现金库存信息",operation = OperationType.UPDATE)
    public int updateCashInventory(CashInventory cashInventory){
//        System.out.println("dateTime="+cashInventory.getDateTime());
        int i=cashInventoryService.updateCashInventory(cashInventory);
        return i;
    }

    /**
     * 删除库存
     * @param cashInventoryId 库存Id
     * @return
     */
    @RequestMapping("/delete")
    @OperLog(message = "删除现金库存信息",operation = OperationType.DELETE)
    public int deleteCashInventory(String cashInventoryId){
        System.out.println(cashInventoryId);
        int i=cashInventoryService.deleteCashInventory(cashInventoryId);
        return i;
    }
}

package com.ssaw.InventoryManagement.controller;

import com.ssaw.GlobalManagement.util.DateTimeUtil;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import com.ssaw.InventoryManagement.entity.SecuritiesClosedPayInventory;
import com.ssaw.InventoryManagement.service.SecuritiesClosedPayInventoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:证券应收应付库存
 * Datetime:2020/9/14
 * Author:sunH
 */
@RestController
@RequestMapping("securitiesClosedPayInventory")
public class SecuritiesClosedPayInventoryController {
    @Resource
    SecuritiesClosedPayInventoryService securitiesClosedPayInventoryService;

    @Resource
    DbUtil dbUtil;

    /**
     * 按条件分页查询
     * @param page 每页条数
     * @param limit 页数
     * @param securitiesType    证券类型
     * @param dateTime  日期
     * @return
     */
    @RequestMapping("select")
    public Map<String,Object> selectSecuritiesClosedPayInventory(String page, String limit,String securitiesType,String dateTime){
        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map =  securitiesClosedPayInventoryService.selectSecuritiesClosedPayInventory(limit,page,securitiesType,dateTime);
        //从结果集中拿出结果
        List<SecuritiesClosedPayInventory> securitiesClosedPayInventoryList = (List<SecuritiesClosedPayInventory>) map.get("securitiesClosedPayInventory");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",securitiesClosedPayInventoryList);
        //返回数据
        return json;
    }

    @RequestMapping("insert")
    public int insertSecuritiesClosedPayInventory(SecuritiesClosedPayInventory securitiesClosedPayInventory){
        securitiesClosedPayInventory.setSecuritiesClosedPayInventoryId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.SCPI));
        securitiesClosedPayInventory.setDateTime(DateTimeUtil.getSystemDateTime("yyyy-MM-dd"));
        System.out.println("业务类型是"+(securitiesClosedPayInventory.getSecurityPeriodFlag()));
        int i = securitiesClosedPayInventoryService.insertSecuritiesClosedPayInventory(securitiesClosedPayInventory);
        return i;
    }

    @RequestMapping("delete")
    public int deleteSecuritiesClosedPayInventory(String securitiesClosedPayInventoryId){
        int i = securitiesClosedPayInventoryService.deleteSecuritiesClosedPayInventory(securitiesClosedPayInventoryId);
        return i;
    }

    @RequestMapping("update")
    public int updateSecuritiesClosedPayInventory(SecuritiesClosedPayInventory securitiesClosedPayInventory){
        System.out.println(securitiesClosedPayInventory);
        int i = securitiesClosedPayInventoryService.updateSecuritiesClosedPayInventory(securitiesClosedPayInventory);
        return i;
    }
}

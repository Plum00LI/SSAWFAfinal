package com.ssaw.InventoryManagement.service.impl;


import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import com.ssaw.InventoryManagement.entity.CashInventory;
import com.ssaw.InventoryManagement.mapper.CashInventoryMapper;
import com.ssaw.InventoryManagement.service.CashInventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:    CashInventoryServiceImpl
 * Package:    com.ssaw.InventoryManagement.service.impl
 * Description: 现金库存
 * Version:   1.0
 * Datetime:    2020/9/1   9:35
 * Author:   SYT
 */
@Service
@Transactional
public class CashInventoryServiceImpl implements CashInventoryService {
    /**
     * 注入cashInventory的mapper
     */
    @Resource
    CashInventoryMapper cashInventoryMapper;

    /**
     * 分页查询所有现金库存的方法
     * @param pageSize  当前查询页数
     * @param page  分页数据条目数
     * @return  查询的结果集Map
     */
    @Override
    public Map<String, Object> selectCashInventory(String pageSize, String page,String accountId,String dateTime,String fundId) {
        //创建一个结果集Map用于存放两个结果变量
        Map<String,Object> resultMap=new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize=10;
        //判断传入的pageSize是否为null/空
        if (pageSize!=null && !pageSize.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_pageSize=Integer.parseInt(pageSize);
        }
        //定义一个分页页码变量
        int v_page=1;
        //判断传入的page是否为null/空
        if (page!=null && !page.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_page=Integer.parseInt(page);
        }
        //输出测试
        String sql="";

        System.out.println("accountID+"+accountId);
        System.out.println("dateTime"+dateTime);
        //现金账户
        if(accountId!=null && !accountId.equals("")){
            sql=sql+" and accountId ='"+accountId+"'";
        }
        //日期
        if(dateTime!=null && !dateTime.equals("")){
            sql=sql+" and dateTime='"+dateTime+"'";
        }


        //创建一个Map，用于存储过程的调用传值
        Map<String,Object> map=new HashMap<>();
        String p_tableName="( select c.cashInventoryId,c.fundId,c.cashBlance,c.accountId,c.dateTime,c.securityPeriodFlag,c.cashInventoryDesc,a.accountName "+
        " from " + SysTableNameListUtil.CI+" c join "+SysTableNameListUtil.A +" a on c.accountId=a.accountId and c.fundId='"+fundId+"' )";

        System.out.println(sql);
        System.out.println(p_tableName);
        //传入存储过程需要查询的表名
        map.put("p_tableName",p_tableName);
        //传入查询条件
        map.put("p_condition",sql);
        //传入分页显示条数
        map.put("p_pageSize",v_pageSize);
        //传入分页页码
        map.put("p_page",v_page);
        //创建out参数，返回数据总条数
        map.put("p_count",0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor",null);
        //调用Mapper执行查询
        cashInventoryMapper.selectCashInventory(map);
        //接收返回数据
        List<CashInventory> cashInventoryList= (List<CashInventory>) map.get("p_cursor");
        //接收返回总条数
        int v_count= (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("cashInventory",cashInventoryList);
        resultMap.put("count",v_count);
        //返回结果集Map
        return resultMap;
    }

    /**
     * 增加现金库存
     * @param cashInventory
     * @return
     */
    public int insertCashInventory(CashInventory cashInventory) {

        return cashInventoryMapper.insertCashInventory(cashInventory);
    }

    /**
     * 修改现金库存信息
     * @param cashInventory
     * @return
     */
    public int updateCashInventory(CashInventory cashInventory) {
        System.out.println("cashInventory+"+cashInventory);
        return cashInventoryMapper.updateCashInventory(cashInventory);
    }

    /**
     * 批量删除
     * @param cashInventoryId
     * @return
     */
    @Override
    public int deleteCashInventory(String cashInventoryId) {
        String[] cashInventoryIds=cashInventoryId.split(",");
        ArrayList<Object> cashInventoryIdList=new ArrayList<>();
        for (String id:cashInventoryIds){
            cashInventoryIdList.add(id);
        }
        return cashInventoryMapper.deleteCashInventory(cashInventoryIdList);
    }

}

package com.ssaw.InventoryManagement.service.impl;

import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import com.ssaw.InventoryManagement.entity.CashClosedPayInventory;
import com.ssaw.InventoryManagement.mapper.CashClosedPayInventoryMapper;
import com.ssaw.InventoryManagement.service.CashClosedPayInventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:现金应收应付库存模块
 * @Description:实现类
 * @author:孙浩
 * @create:2020-09-01
 */
@Service
@Transactional
public class CashClosedPayInventoryServiceImpl implements CashClosedPayInventoryService {
    @Resource
    CashClosedPayInventoryMapper cashClosedPayInventoryMapper;

    @Override
    public int insertCashClosedPayInventory(CashClosedPayInventory cashClosedPayInventory) {
        return cashClosedPayInventoryMapper.insertCashClosedPayInventory(cashClosedPayInventory);
    }

    @Override
    public int deleteCashClosedPayInventory(String cashClosedPayInventoryId) {
        //将id转为数据
        String[] split = cashClosedPayInventoryId.split(",");
        //创建存放id的集合
        ArrayList<Object> cashClosedPayInventoryIdList = new ArrayList<>();
        for (String id : split) {
            //将id存入集合
            cashClosedPayInventoryIdList.add(id);
        }
        return cashClosedPayInventoryMapper.deleteCashClosedPayInventory(cashClosedPayInventoryIdList);
    }

    @Override
    public int updateCashClosedPayInventory(CashClosedPayInventory cashClosedPayInventory) {
        return cashClosedPayInventoryMapper.updateCashClosedPayInventory(cashClosedPayInventory);
    }

    @Override
    public Map<String, Object> selectCashClosedPayInventory(String pageSize, String page,String businessType,String businessDate) {
        //创建一个结果集Map用于存放两个结果变量
        Map<String, Object> resultMap = new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize = 0;
        //判断v_pageSize是否为空
        if (pageSize != null && !pageSize.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_pageSize=Integer.parseInt(pageSize);
        }
        //定义一个分页页码变量
        int v_page = 0;
        //判断传入的page是否为null/空
        if (page != null && !page.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_page=Integer.parseInt(page);
        }


        int v_businessType = 0;
        StringBuffer sqlWhere=new StringBuffer();
        if(businessDate != null && !businessDate.equals("")){
            sqlWhere.append(" AND businessDate LIKE  '%"+businessDate+"%'" );
        }
        if(businessType != null && !businessType.equals("")){
            v_businessType=Integer.parseInt(businessType);
            sqlWhere.append(" AND businessType LIKE  '%"+v_businessType+"%'" );
        }


        String v_tableName = "(select * from " + SysTableNameListUtil.CCPI +" c " +
                "join (select fundName,fundId from "+ SysTableNameListUtil.F+" ) f " +
                "on c.fundId=f.fundId "+
                "join (select accountId,accountName from "+ SysTableNameListUtil.A+") a "+
                "on c.accountId=a.accountId)";


        


        //创建一个Map，用于存储过程的调用传值
        Map<String,Object> map = new HashMap<>();
        //传入存储过程需要的查询的表名
        map.put("p_tableName",v_tableName);
        //传入查询条件
        map.put("p_condition",sqlWhere.toString());
        //传入分页显示条数
        map.put("p_pageSize",v_pageSize);
        //传入分页页码
        map.put("p_page",v_page);
        //创建out参数，返回数据总条数
        map.put("p_count",0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor",null);
        //调用Mapper执行查询
        cashClosedPayInventoryMapper.selectCashClosedPayInventory(map);
        //接收返回数据
        List<CashClosedPayInventory> cashClosedPayInventoryList = (List<CashClosedPayInventory>) map.get("p_cursor");
        //接收返回总条数
        int v_count = (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("cashClosedPayInventoryList",cashClosedPayInventoryList);
        resultMap.put("count",v_count);
        System.out.println(resultMap.get("cashClosedPayInventoryList"));
        //返回结果集Map
        return resultMap;
    }
}

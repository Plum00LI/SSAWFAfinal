package com.ssaw.InventoryManagement.service.impl;

import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import com.ssaw.InventoryManagement.entity.SecuritiesClosedPayInventory;
import com.ssaw.InventoryManagement.mapper.SecuritiesClosedPayInventoryMapper;
import com.ssaw.InventoryManagement.service.SecuritiesClosedPayInventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:证券应收应付库存
 * Datetime:2020-09-14
 * Author:sunH
 */
@Service
@Transactional
public class SecuritiesClosedPayInventoryServiceImpl implements SecuritiesClosedPayInventoryService {

    @Resource
    SecuritiesClosedPayInventoryMapper securitiesClosedPayInventoryMapper;

    /**
     *
     * @param pageSize  每页条数
     * @param page    页数
     * @param securitiesType    证券应收应付类型
     * @param dateTime  日期
     * @return
     */
    @Override
    public Map<String, Object> selectSecuritiesClosedPayInventory(String pageSize, String page, String securitiesType, String dateTime) {
        //创建一个结果集Map用于存放两个结果变量
        Map<String,Object> resultMap=new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize=10;
        //判断传入的pageSize是否为null/空
        if (pageSize!=null &&!pageSize.equals("")){
            v_pageSize=Integer.parseInt(pageSize);
        }
        //定义一个分页页码变量
        int v_page=1;
        //判断传入的page是否为null/空
        if (page!=null && !page.equals("")){
            v_page=Integer.parseInt(page);
        }
        //创建sql
        String sql="";
        //证券类型
        if (securitiesType!=null && !securitiesType.equals("")){
            sql=sql+" and securitiesType="+securitiesType+" ";
        }
        //日期
        if (dateTime!=null && !dateTime.equals("")){
            sql=sql+" and dateTime='"+dateTime+"'";
        }
        //创建一个Map，用于存储过程的调用传值
        Map<String,Object> map=new HashMap<>();



        //关联查询
        String p_tableName="( select * from "+ SysTableNameListUtil.SCPI +
                " s join (select securitiesId, securitiesName from "+SysTableNameListUtil.SE+")e on s.securitiesId=e.securitiesId )";


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
        securitiesClosedPayInventoryMapper.selectSecuritiesClosedPayInventory(map);
        //接收返回数据
        List<SecuritiesClosedPayInventory> securitiesClosedPayInventoryList= (List<SecuritiesClosedPayInventory>) map.get("p_cursor");
        //接收返回总条数
        int v_count= (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("securitiesClosedPayInventory",securitiesClosedPayInventoryList);
        resultMap.put("count",v_count);
        //返回结果集Map
        return resultMap;
    }

    /**
     * 增加证券应收应付库存
     * @param securitiesClosedPayInventory
     * @return
     */
    @Override
    public int insertSecuritiesClosedPayInventory(SecuritiesClosedPayInventory securitiesClosedPayInventory) {
        return securitiesClosedPayInventoryMapper.insertSecuritiesClosedPayInventory(securitiesClosedPayInventory);
    }

    /**
     * 修改证券应收应付库存信息
     * @param securitiesClosedPayInventory
     * @return
     */
    @Override
    public int updateSecuritiesClosedPayInventory(SecuritiesClosedPayInventory securitiesClosedPayInventory) {
        return securitiesClosedPayInventoryMapper.updateSecuritiesClosedPayInventory(securitiesClosedPayInventory);
    }

    /**
     * 批量删除
     * @param securitiesClosedPayInventoryId
     * @return
     */
    @Override
    public int deleteSecuritiesClosedPayInventory(String securitiesClosedPayInventoryId) {
        String[] securitiesClosedPayInventoryIds=securitiesClosedPayInventoryId.split(",");
        ArrayList<Object> securitiesClosedPayInventoryIdList=new ArrayList<>();
        for (String id : securitiesClosedPayInventoryIds) {
            securitiesClosedPayInventoryIdList.add(id);
        }
        return securitiesClosedPayInventoryMapper.deleteSecuritiesClosedPayInventory(securitiesClosedPayInventoryIdList);
    }

    @Override
    public void deleteSecuritiesClosedPayInventoryDate(String securitiesId,String toDay, Integer securitiesType) {
        securitiesClosedPayInventoryMapper.deleteSecuritiesClosedPayInventoryDate(securitiesId,toDay,securitiesType);
    }
}

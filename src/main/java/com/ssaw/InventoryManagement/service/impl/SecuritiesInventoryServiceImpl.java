package com.ssaw.InventoryManagement.service.impl;

import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import com.ssaw.InventoryManagement.entity.SecuritiesInventory;
import com.ssaw.InventoryManagement.mapper.SecuritiesInventoryMapper;
import com.ssaw.InventoryManagement.service.SecuritiesInventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*@program: TescComment
*@Description:证券库存实现类
*@author: 瞿平
*@version:1.0
*@create: 2020-09-01
*/
@Service
public class SecuritiesInventoryServiceImpl implements SecuritiesInventoryService {
    //调用证券库存Dao类
    @Resource
    SecuritiesInventoryMapper securitiesInventoryMapper;
    //调用工具类
    @Resource
    DbUtil dbUtil;

    /**
     * 证券库存查询方法
     * @return
     */
    @Override
    public List<SecuritiesInventory> selectSecuritiesInventory() {
        return securitiesInventoryMapper.selectSecuritiesInventory();
    }

    /**
     * 证券库存增加方法
     * @param securitiesInventory 证券库存实体类
     * @return
     */
    @Override
    public int insertSecuritiesInventory(SecuritiesInventory securitiesInventory) {
//        securitiesInventory.setFundId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.F));
    return securitiesInventoryMapper.insertSecuritiesInventory(securitiesInventory);
    }

    /**
     * 证券库存多行删除以及单行删除
     * @param securitiesInventoryId 证券库存Id
     * @return
     */
    @Override
    public int deleteSecuritiesInventory(String securitiesInventoryId) {
        //切割字符串
        String[] split = securitiesInventoryId.split(",");
        ArrayList<Object> list=new ArrayList<>();
        for (String id : split) {
            list.add(id);
        }
        return securitiesInventoryMapper.deleteSecuritiesInventory(list);
    }

    /**
     * 证券库存修改方法
     * @param securitiesInventory 证券库存实体对象
     * @return
     */
    @Override
    public int updateSecuritiesInventory(SecuritiesInventory securitiesInventory) {
        return securitiesInventoryMapper.updateSecuritiesInventory(securitiesInventory);
    }

    /**
     * 证券库存分页查询
     * @param pageSize 条数
     * @param page 页码
     * @param dateTime 业务日期
     * @param securitiesId 证券代码
     * @param securitiesName 证券名称
     * @param fundId 基金Id
     * @return
     */
    @Override
    public Map<String, Object> selectSecuritiesInventoryInfo(String pageSize, String page,String dateTime,String securitiesId,String securitiesName,String fundId) {
        //创建一个结果集Map,用于存放两个结果变量
        Map<String, Object> resultMap = new HashMap<>();
        //定义一个分页条数变量
        int s_pageSize = 0;
        //判断传入的pageSize是否为null
        if (pageSize != null && !pageSize.equals("")) {
            //强转为int类型
            s_pageSize = Integer.parseInt(pageSize);
        }
        //定义一个分页码变量
        int s_page = 0;
        //判断传入的page是否为空
        if (page != null && !page.equals("")) {
            //强转为int类型
            s_page = Integer.parseInt(page);
        }

        StringBuffer sqlWhere = new StringBuffer();

        //高级搜索查询条件 业务日期
        if (dateTime!=null && !dateTime.equals("")){
            sqlWhere.append(" and dateTime like '%"+dateTime+"%'");
        }
        //高级搜索查询条件 证券名称
        if (securitiesName!=null && !securitiesName.equals("")){
            sqlWhere.append(" and securitiesName like '%"+securitiesName+"%'");
        }
        String tableName="(select * from " + SysTableNameListUtil.SI +" s join (select securitiesName，securitiesId from "+ SysTableNameListUtil.SE+" )  e on s.securitiesId=e.securitiesId "
                +"join (select fundId from "+ SysTableNameListUtil.F+" )  f on s.fundId=f.fundId)";
        System.out.println("语句"+tableName);

        //创建一个Map,用来调用存储过程
        Map<String, Object> map = new HashMap<>();
        //传入存储过程要查询的表名
        map.put("p_tableName", tableName);
        //传入查询条件
        map.put("p_condition", sqlWhere.toString());
        //传入分页显示条数
        map.put("p_pageSize", s_pageSize);
        //传入分页页码
        map.put("p_page", s_page);
        //创建out参数，返回数据总条数
        map.put("p_count", 0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor", null);
        //调用mapper执行查询
        securitiesInventoryMapper.selectSecuritiesInventoryInfo(map);
        List<SecuritiesInventory> securitiesInventoryList = (List<SecuritiesInventory>) map.get("p_cursor");
        System.out.println("集合信息"+securitiesInventoryList.toString());
        //接收返回总条数
        int m_count = (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("securitiesInventoryList", securitiesInventoryList);
        resultMap.put("count", m_count);
        //返回结果集Map
        return resultMap;
    }

}


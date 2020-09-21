package com.ssaw.InventoryManagement.service.impl;

import com.ssaw.InventoryManagement.entity.TaInventory;
import com.ssaw.InventoryManagement.mapper.TaInventoryMapper;
import com.ssaw.InventoryManagement.service.TaInventoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:    TaInventoryServiceImpl
 * Package:    com.ssaw.InventoryManagement.service.impl
 * Description:
 * Version:   1.0
 * Datetime:    2020/9/1   9:41
 * Author:   SYT
 */
@Service
@Transactional
public class TaInventoryServiceImpl implements TaInventoryService {
    @Resource
    TaInventoryMapper taInventoryMapper;

    /**
     * 分页查询
     * @param pageSize  每页条数
     * @param page      页数
     * @return
     */
    @Override
    public Map<String, Object> selectTaInventory(String pageSize, String page ,String dateTime) {
        //创建一个结果集Map用于存放两个结果变量
        Map<String,Object> resultMap=new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize=0;
        //判断传入的pageSize是否为null/空
        if (pageSize!=null&&!pageSize.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_pageSize=Integer.parseInt(pageSize);
        }
        //定义一个分页页码变量
        int v_page = 0;
        //判断传入的page是否为null/空
        if (page!=null&&!page.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_page=Integer.parseInt(page);
        }
        String sql="";

        System.out.println("dateTime"+dateTime);

        if(dateTime!=null && !dateTime.equals("")){
            sql=sql+" and dateTime='"+dateTime+"'";
        }

        //创建一个Map，用于存储过程的调用传值
        Map<String,Object> map=new HashMap<>();
        //传入存储过程需要查询的表名
        map.put("p_tableName","taInventory");
        //传入查询条件
        map.put("p_condition",sql);
        //传入分页显示条数
        map.put("p_pageSize",v_pageSize);
        //传入分页页码
        map.put("p_page",v_page);
        //创建out参数，返回数据总条数
        map.put("count",0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor",null);
        //调用Mapper执行查询
        taInventoryMapper.selectTaInventory(map);
        //接收返回数据
        List<TaInventory> taInventoryList= (List<TaInventory>) map.get("p_cursor");
        //接收返回总条数
        int v_count= (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("taInventory",taInventoryList);
        resultMap.put("count",v_count);
        //返回结果集Map
       return resultMap;
    }

    /**
     *  增加TA库存
     * @param taInventory
     * @return
     */
    public int insertTaInventory(TaInventory taInventory) {
        return taInventoryMapper.insertTaInventory(taInventory);
    }

    /**
     * 修改TA库存信息
     * @param taInventory
     * @return
     */
    public int updateTaInventory(TaInventory taInventory) {
        return taInventoryMapper.updateTaInventory(taInventory);
    }

    /**
     * 根据Id删除
     * @param taInventoryId
     * @return
     */
    @Override
    public int deleteTaInventory(String taInventoryId) {
        String[] taInventoryIds=taInventoryId.split(",");
        ArrayList<Object> taInventoryIdList=new ArrayList<>();
        for (String id : taInventoryIds) {
            taInventoryIdList.add(id);
        }
        return taInventoryMapper.deleteTaInventory(taInventoryIdList);
    }
}

package com.ssaw.InventoryManagement.controller;

import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.OperationType;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import com.ssaw.InventoryManagement.entity.TaInventory;
import com.ssaw.InventoryManagement.service.TaInventoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:    TaInventoryController
 * Package:    com.ssaw.InventoryManagement.controller
 * Description: TA库存
 * Datetime:    2020/9/1   10:18
 * Author:   SYT
 */
@RestController
@RequestMapping("/taInventory")
public class TaInventoryController {
    @Resource
    TaInventoryService taInventoryService;

    @Resource
    DbUtil dbUtil;

    /**
     * 分页查询
     * @return
     */
    @RequestMapping("/select")
    @OperLog(message = "分页查询TA库存信息",operation = OperationType.QUERY)
    public Map<String,Object> selectTaInventory(String page,String limit,String dateTime){
        Map<String,Object> map=taInventoryService.selectTaInventory(limit,page,dateTime);
        //从结果集中拿出结果
        List<TaInventory> taInventoryList= (List<TaInventory>) map.get("taInventory");
        int count= (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String,Object> json=new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",taInventoryList);
        //返回数据
        return json;
    }

    /**
     * 增加TA库存信息
     * @param taInventory
     * @return
     */
    @RequestMapping("/insert")
    @OperLog(message = "增加TA库存信息",operation = OperationType.ADD)
    public int insertTaInventory(TaInventory taInventory){
        //设置TA库存"100001"Id
        taInventory.setTaInventoryId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.TI));
        //是否为期初数据，0不是，1 是"ertyuio"
        taInventory.setSecurityPeriodFlag(1);
        //基金Id
        taInventory.setFundId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.F));

        System.out.println(taInventory);
        int i=taInventoryService.insertTaInventory(taInventory);
        return i;
    }

    /**
     * 修改TA库存信息
     * @param taInventory
     * @return
     */
    @RequestMapping("/update")
    @OperLog(message = "修改TA库存信息",operation = OperationType.UPDATE)
    public int updateTaInventory(TaInventory taInventory){
        System.out.println(taInventory);
        int i=taInventoryService.updateTaInventory(taInventory);
        return i;
    }

    /**
     * 删除库存
     * @param taInventoryId
     * @return
     */
    @RequestMapping("/delete")
    @OperLog(message = "删除TA库存信息",operation = OperationType.DELETE)
    public int deleteTaInventory(String taInventoryId){
        System.out.println(taInventoryId);
        int i=taInventoryService.deleteTaInventory(taInventoryId);
        return i;
    }
}

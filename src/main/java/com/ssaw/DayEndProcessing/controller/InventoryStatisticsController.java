package com.ssaw.DayEndProcessing.controller;

import com.ssaw.DayEndProcessing.entity.InventoryStatistics;
import com.ssaw.DayEndProcessing.service.InventoryStatisticsService;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.OperationType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * ClassName:    InventoryStatisticsController
 * Package:    com.ssaw.DayEndProcessing.controller
 * Description:     库存统计
 * Version:
 * Datetime:    2020/9/9   17:19
 * Author:   SYT
 */
@RestController
public class InventoryStatisticsController {

    @Resource
    InventoryStatisticsService inventoryStatisticsService;

    /**
     * 库存统计显示在网页的数据
     * @param fundId
     * @param dateTime
     * @param invId
     * @return
     */
    @RequestMapping("/selectInventory")
    @OperLog(message = "库存统计显示在网页的数据",operation = OperationType.QUERY)
    public HashMap selectInventory(String fundId, String dateTime, String invId){
        System.out.println("controller的dateTime"+dateTime);
        List<InventoryStatistics> inventoryEntities = inventoryStatisticsService.selectInventoryStatistics(fundId,dateTime,invId);
        HashMap userMap = new HashMap();
        userMap.put("count",10);
        userMap.put("code",0);
        userMap.put("msg","");
        userMap.put("data",inventoryEntities);
        return userMap;
    }

}

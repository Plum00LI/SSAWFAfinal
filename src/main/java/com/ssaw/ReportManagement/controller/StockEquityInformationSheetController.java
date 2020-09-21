package com.ssaw.ReportManagement.controller;

import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.OperationType;
import com.ssaw.ReportManagement.entity.StockEquityInformationSheet;
import com.ssaw.ReportManagement.service.StockEquityInformationSheetService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:    StockEquityInformationSheetController
 * Package:    com.ssaw.ReportManagement.controller
 * Description: 股票权益信息表
 * Version:     1.0
 * Datetime:    2020/9/18 14:51
 * Author:   阙魁
 */
@RestController
@RequestMapping("StockEquityInformationSheet")
public class StockEquityInformationSheetController {

    @Resource
    StockEquityInformationSheetService stockEquityInformationSheetService;

    @OperLog(message = "查询股票权益信息表",operation = OperationType.QUERY)
    @RequestMapping("selectStockEquityInformationSheet")
    public Map<String, Object> selectStockEquityInformationSheet(String page, String limit, String startTime, String endTime, String equitiesTypes){
        System.out.println("进入股票权益信息表controller");

        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map = stockEquityInformationSheetService.selectStockEquityInformationSheet(limit,page,startTime,endTime,equitiesTypes);
        System.out.printf(map.toString());
        //从结果集中拿出结果
        List<StockEquityInformationSheet> stockEquityInformationSheetList = (List<StockEquityInformationSheet>) map.get("stockEquityInformationSheetList");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",stockEquityInformationSheetList);
        //返回数据
        return json;
    }
}

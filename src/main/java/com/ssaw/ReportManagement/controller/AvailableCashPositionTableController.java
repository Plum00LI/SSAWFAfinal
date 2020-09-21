package com.ssaw.ReportManagement.controller;

import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.OperationType;
import com.ssaw.ReportManagement.entity.AvailableCashPositionTable;
import com.ssaw.ReportManagement.service.AvailableCashPositionTableService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName:    AvailableCashPositionTableController
 * Package:    com.ssaw.ReportManagement.controller
 * Description: 可用现金头寸表
 * Version:     1.0
 * Datetime:    2020/9/18 14:51
 * Author:   阙魁
 */
@RestController
@RequestMapping("AvailableCashPositionTable")
public class AvailableCashPositionTableController {
    @Resource
    AvailableCashPositionTableService availableCashPositionTableService;

    @OperLog(message = "查询可用现金头寸表",operation = OperationType.QUERY)
    @RequestMapping("selectAvailable")
    public Map<String,Object> selectAvailable(String page, String limit,String dateTime){
        System.out.println("进入可用现金寸头报表controller");

        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map = availableCashPositionTableService.selectAvailable(limit,page,dateTime);
        System.out.printf(map.toString());
        //从结果集中拿出结果
        List<AvailableCashPositionTable> availableCashPositionTableList = (List<AvailableCashPositionTable>) map.get("availableCashPositionTableList");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",availableCashPositionTableList);
        //返回数据
        return json;
    }
}

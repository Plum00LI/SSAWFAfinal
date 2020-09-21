package com.ssaw.BusinessData.controller;

import com.ssaw.BusinessData.entity.EquityData;
import com.ssaw.BusinessData.service.EquityDataService;
import com.ssaw.GlobalManagement.util.DateTimeUtil;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:权益数据模块
 * @Description:控制类
 * @author:孙浩
 * @create:2020-09-01
 */

@RestController
public class EquityDataController {
    @Resource
    EquityDataService equityDataService;
    @Resource
    DbUtil dbUtil;

    @RequestMapping("insertEquityData")
    public int insertEquityData(EquityData equityData){
        equityData.setEquityDataId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.ED));
        equityData.setDateTime(DateTimeUtil.getSystemDateTime("yyyy-MM-dd"));
        int i = equityDataService.insertEquityData(equityData);
        return i;
    }

    @RequestMapping("deleteEquityData")
    public int deleteEquityData(String equityDataId){
        int i = equityDataService.deleteEquityData(equityDataId);
        return i;
    }

    @RequestMapping("updateEquityData")
    public int updateEquityData(EquityData equityData){
        System.out.println(equityData);
        return equityDataService.updateEquityData(equityData);
    }

    @RequestMapping("selectEquityData")
    public Map<String,Object> selectEquityData(String page,String limit, String  equitiesType, String equitiesExright){
        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map = equityDataService.selectEquityData(limit,page,equitiesType,equitiesExright);
        List<EquityData> equityDataList = (List<EquityData>) map.get("equityDataList");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> equityDataMap = new HashMap<>();
        equityDataMap.put("code",0);
        equityDataMap.put("msg","");
        equityDataMap.put("count",count);
        equityDataMap.put("data",equityDataList);
        //返回数据
        System.out.println(equityDataList);
        return equityDataMap;
    }
}

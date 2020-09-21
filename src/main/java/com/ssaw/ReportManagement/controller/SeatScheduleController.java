package com.ssaw.ReportManagement.controller;

import com.ssaw.ReportManagement.entity.SeatSchedule;
import com.ssaw.ReportManagement.service.SeatScheduleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("SeatSchedule2")
public class SeatScheduleController {
    @Resource
    SeatScheduleService seatScheduleService;

    @RequestMapping("selectSeatSchedule2")
    public Map<String,Object> selectSeatSchedule(String page, String limit, String seateId, String datefind, String settlementDate){
        System.out.println(page+limit);
        System.out.println(seateId);
        System.out.println(datefind);
        System.out.println(settlementDate);
        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map = seatScheduleService.selectSeatSchedule(limit,page,seateId,datefind,settlementDate);
        System.out.printf(map.toString());
        //从结果集中拿出结果
        List<SeatSchedule> securitiesList = (List<SeatSchedule>) map.get("securities");
        int count = (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",securitiesList);
        //返回数据
        return json;
    }

}

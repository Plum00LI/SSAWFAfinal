package com.ssaw.ReportManagement.controller;/**
 * @program: TescComment
 * @Description:实体类
 * @author: 瞿平
 * @version:1.0
 * @create: 2020-09-01
 */

import com.ssaw.ReportManagement.entity.ClosingDate;
import com.ssaw.ReportManagement.service.ClosingDateService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *@program: FA
 *@description: 成交清算日数据控制类
 *@author: 瞿平
 *@create: 2020-09-19 14:53
 **/
@RestController
@RequestMapping("ClosingDateController")
public class ClosingDateController {

    @Resource
    ClosingDateService closingDateService;

    @RequestMapping("selectClosingDate")
    public HashMap selectClosingDate(int page,int limit,String dateTime) {
        System.out.println("进入查询方法");
        HashMap cdsMap = closingDateService.selectClosingDate(dateTime);
        int count = (int) cdsMap.get("count");
        ArrayList<ClosingDate> closingDateList = (ArrayList<ClosingDate>) cdsMap.get("list");
        System.out.println("集合数据=" + closingDateList);
        System.out.println(count);
        HashMap hashMap = new HashMap<>();
        hashMap.put("msg", "");
        hashMap.put("code", 0);
        hashMap.put("count", count);
        hashMap.put("data", closingDateList);
        return hashMap;
    }
}

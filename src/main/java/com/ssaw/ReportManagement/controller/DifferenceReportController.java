package com.ssaw.ReportManagement.controller;

import com.ssaw.ReportManagement.entity.DifferenceReport;
import com.ssaw.ReportManagement.service.DifferenceReportService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * @program:TescComment
 * @Eescription:成交清算轧差
 * @author:黄庆
 * @Version:1.0
 * @create:2020-09-01
 */
@RestController
public class DifferenceReportController {
    @Resource
    DifferenceReportService differenceReportService;
    @RequestMapping("/selectDifferenceReport")
    public HashMap selectDifferenceReport(String dateTime){
        System.out.println("controller的dateTime"+dateTime);
        List<DifferenceReport> differenceReportList = differenceReportService.selectDifferenceReport(dateTime);
        HashMap userMap = new HashMap();
        userMap.put("count",10);
        userMap.put("code",0);
        userMap.put("msg","");
        userMap.put("data",differenceReportList);
        return userMap;
    }
}

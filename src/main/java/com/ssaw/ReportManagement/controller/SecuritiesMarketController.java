package com.ssaw.ReportManagement.controller;

import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.OperationType;
import com.ssaw.ReportManagement.entity.SecuritiesMarket;
import com.ssaw.ReportManagement.service.SecuritiesMarketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * ClassName:    SecuritiesMarketController
 * Package:    com.ssaw.ReportManagement.controller
 * Description:
 * Version:
 * Datetime:    2020/9/21   14:37
 * Author:   SYT
 */
@RestController
public class SecuritiesMarketController {
    @Resource
    SecuritiesMarketService securitiesMarketService;

    @RequestMapping("selectSecuritiesMarket")
    @OperLog(message = "证券市场变动显示在网页的数据",operation = OperationType.QUERY)
    public HashMap selectSecuritiesMarket(String dateTime){
        //service调用select方法
        HashMap hashMap = (HashMap) securitiesMarketService.selectSecuritiesMarket(dateTime);
        //获得count
        int count = (int) hashMap.get("count");
        //获得list
        ArrayList<SecuritiesMarket> smcpList = (ArrayList<SecuritiesMarket>) hashMap.get("list");
        HashMap<Object, Object> smcMap = new HashMap<>();
        // code，msg,count,data
        smcMap.put("code",0);
        smcMap.put("msg","");
        smcMap.put("count",count);
        smcMap.put("data",smcpList);
        return smcMap;

    }
}

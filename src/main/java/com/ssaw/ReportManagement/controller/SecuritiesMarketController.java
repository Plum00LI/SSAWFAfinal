package com.ssaw.ReportManagement.controller;

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
    public HashMap selectSecuritiesMarket(String dateTime){
        System.out.println("证券市场变动的控制类");
        HashMap hashMap = (HashMap) securitiesMarketService.selectSecuritiesMarket(dateTime);
        int count = (int) hashMap.get("count");
        ArrayList<SecuritiesMarket> smcpList = (ArrayList<SecuritiesMarket>) hashMap.get("list");
        HashMap<Object, Object> smcMap = new HashMap<>();
        smcMap.put("code",0);
        smcMap.put("msg","");
        smcMap.put("count",count);
        smcMap.put("data",smcpList);
        return smcMap;

    }
}

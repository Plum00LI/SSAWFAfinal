package com.ssaw.BusinessProcessing.controller;

import com.ssaw.BusinessProcessing.entity.Settlement;
import com.ssaw.BusinessProcessing.service.SettlementService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 * create by: 曾钦辉
 * description: 交易结算控制类
 * create time: 2020/9/10 9:32
 *
  * @Param: null
 * @return
 */
@RestController
public class SettlementController {
    @Resource
    SettlementService settlementService;
    @RequestMapping("selectSettlement")
    public HashMap selectSettlement(int page, int limit, String dateTime, String transactionDataMode,String status){
        HashMap hashMap = settlementService.selectSettlement(page,limit,dateTime,transactionDataMode,status);
        int count = (int) hashMap.get("p_count");
        List<Settlement> settlementList = (List<Settlement>) hashMap.get("p_cursor");
        System.out.println("总条数："+count);
        System.out.println("page="+page+",limit="+limit+",dateTime="+dateTime+
                ",transactionDataMode="+transactionDataMode+"status="+status);
        HashMap tranMap = new HashMap();
        tranMap.put("count",count);
        tranMap.put("code",0);
        tranMap.put("msg","");
        tranMap.put("data",settlementList);
        System.out.println("数据"+settlementList);
        System.out.println("交易："+settlementList.size());
        return tranMap;
    }
    @RequestMapping("deleteSettlement")
    public int deleteSettlement(String transactionDataId){
        return settlementService.deleteSettlement(transactionDataId);
    }

    @RequestMapping("updateSettlement")
    public int updateTransactionData(String settlement){
        return settlementService.updateSettlement(settlement);
    }

    @RequestMapping("updateSettlementTwo")
    public int updateSettlementTwo(String settlement){
        return settlementService.updateSettlementTwo(settlement);
    }
}

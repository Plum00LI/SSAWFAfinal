package com.ssaw.BusinessProcessing.controller;

import com.ssaw.BusinessProcessing.entity.TaSettlement;
import com.ssaw.BusinessProcessing.service.TaSettlementService;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.OperationType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 查询
 */
@RestController
@RequestMapping("TaSettlement")
public class TaSettlementController {
    @Resource
    TaSettlementService taSettlementService;
    @Resource
    DbUtil dbUtil;
    @OperLog(message = "查询TA交易结算",operation = OperationType.QUERY)
    @RequestMapping("selectTaSettlement")
    public Map<String,Object> selectTaSettlement(String page,String limit,String dateTime,String transactionType,String status) {
        System.out.println("进来了==================");
        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map = taSettlementService.selectTaSettlement(limit,page,dateTime,transactionType,status);
        //从结果集中拿出结果
        List<TaSettlement> TaSettlementList= (List<TaSettlement>) map.get("TaSettlementList");
        int count= (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String,Object> json=new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",TaSettlementList);
        //返回数据
        return json;

    }
    @OperLog(message = "修改债券信息设置",operation = OperationType.UPDATE)
    @RequestMapping("/updateTaSettlement")
    public int updateTransactionData(String settlement){
        return taSettlementService.updateSettlement(settlement);
    }

    @RequestMapping("/updateTaSettlementTwo")
    public int updateTwoTransactionData(String settlement){
        return taSettlementService.updateSettlementTwo(settlement);
    }
}



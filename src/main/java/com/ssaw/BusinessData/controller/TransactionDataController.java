package com.ssaw.BusinessData.controller;

import com.ssaw.BusinessData.entity.TransactionData;
import com.ssaw.BusinessData.service.TransactionDataService;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * create by: 曾钦辉
 * description: 交易数据Controller控制层
 * create time: 2020/9/1 15:07
 *
  * @Param: null
 * @return
 */
@RestController
public class TransactionDataController {
    @Resource
    TransactionDataService transactionDataService;

    @Resource
    DbUtil dbUtil;

    @RequestMapping("selectTransactionData")
    public HashMap selectTransactionData(int page,int limit,String dateTime,String securitiesName){
        HashMap hashMap = transactionDataService.selectTransactionData(page,limit,dateTime,securitiesName);
        int count = (int) hashMap.get("p_count");
        List<TransactionData> transactionDataList = (List<TransactionData>) hashMap.get("p_cursor");
        System.out.println("总条数："+count);
        System.out.println("page="+page+",limit="+limit+",dateTime="+dateTime+",securitiesName="+securitiesName);
        HashMap tranMap = new HashMap();
        tranMap.put("count",count);
        tranMap.put("code",0);
        tranMap.put("msg","");
        tranMap.put("data",transactionDataList);
        System.out.println("数据"+transactionDataList);
        System.out.println("交易："+transactionDataList.size());
        return tranMap;
    }
    @RequestMapping("insertTransactionData")
    public int insertTransactionData(TransactionData transactionData){
        transactionData.setTransactionDataId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.TD));
        System.out.println(transactionData.getFundId());
        return transactionDataService.insertTransactionData(transactionData);
    }

    @RequestMapping("deleteTransactionData")
    public Map<String,Object> deleteTransactionData(String transactionDataId,String transactionData){
        System.out.println("删除进来了");
        String s = null;
        Map<String, Object> msgMap = new HashMap<>();
        if (transactionDataId!=null&&!transactionDataId.equals("")){
            s=transactionDataService.deleteTransactionData(transactionDataId,null);
        }else {
            s=transactionDataService.deleteTransactionData(null,transactionData);
        }
        if (s.equals("删除成功")){
            msgMap.put("code",0);
        }else {
            msgMap.put("code",1);
        }
        msgMap.put("msg",s);
        return msgMap;
    }

    @RequestMapping("updateTransactionData")
    public int updateTransactionData(TransactionData transactionData){
        System.out.println("1111111111111111111111"+transactionData.getSecuritiesId());
        return transactionDataService.updateTransactionData(transactionData);
    }
}

package com.ssaw.DayEndProcessing.service.impl;


import com.ssaw.DayEndProcessing.entity.AssetValuation;
import com.ssaw.DayEndProcessing.entity.AssetValuationData;
import com.ssaw.DayEndProcessing.mapper.AssetValuationMapper;
import com.ssaw.DayEndProcessing.service.AssetValuationService;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.InventoryManagement.entity.CashClosedPayInventory;
import com.ssaw.InventoryManagement.entity.SecuritiesClosedPayInventory;
import com.ssaw.InventoryManagement.service.CashClosedPayInventoryService;
import com.ssaw.InventoryManagement.service.SecuritiesClosedPayInventoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *@program: FA
 *@description: 资产估值实现类
 *@author: 瞿平
 *@create: 2020-09-11 16:54
 **/
@Service
public class AssetValuationServiceImpl implements AssetValuationService {
    //调用资产估值Dao类
    @Resource
    AssetValuationMapper assetValuationMapper;
    //调用证券应收应付库存serivce类
    @Resource
    SecuritiesClosedPayInventoryService securitiesClosedPayInventoryService;
    //调用现金应收应付库存serivce类
    @Resource
    CashClosedPayInventoryService cashClosedPayInventoryService;
    //调用工具类
    @Resource
    DbUtil dbUtil;

    //查询表格内容
    @Override
    public List<AssetValuationData> selectBiaoge(){
        //插入估值类型和估值状态
        AssetValuationData assetValuation = new AssetValuationData(1,"证券估值增值","未估值");
        AssetValuationData assetValuation1 = new AssetValuationData(2,"清算款","未清算");
        List<AssetValuationData> assetValuationList = new ArrayList();
        assetValuationList.add(assetValuation);
        assetValuationList.add(assetValuation1);
        return assetValuationList;
    }

    // 查询是否处理的实现类
    @Override
    public List<AssetValuation> selectValuationProcessing() {
        return assetValuationMapper.selectValuationProcessing();
    }

    /**
     * 证券估值增值
     * @param fundId 基金代码
     * @param dateTime 业务日期
     * @return
     */
    @Override
    public HashMap selectStockarket(String fundId,String dateTime) {
        HashMap stockarketMap = new HashMap();
        //对证券库存和行情数据进行关联查询，计算出估值增值金额
        stockarketMap.put("p_tableName","(select se.fundId,se.securitiesId,total,SE.securitiesNum,M.closingPrice,\n" +
                "       ROUND((nvl(SE.securitiesNum,0)*nvl(M.closingPrice,0)-total),2)as tootaIPrice,\n" +
                "       SE.securityPeriodFlag  from (select * from securitiesInventory\n" +
                "       where fundId='"+ fundId +"' and DATETIME='"+ dateTime+"') se\n" +
                "           join (select * from market where datetime='"+ dateTime +"') m on se.securitiesId=m.securitiesId)");
        stockarketMap.put("p_condition","");//传入查询条件
        stockarketMap.put("p_pageSize",10);//传入分页显示条数
        stockarketMap.put("p_page",1);//传入分页页码
        stockarketMap.put("p_count",0);//创建out参数，返回数据总条数
        stockarketMap.put("p_cursor",null);//创建out游标变量，返回查询数据
        assetValuationMapper.selectStockarket(stockarketMap);
        return stockarketMap;
    }

    /**
     * 证券应收应付库存
     * @param securitiesClosedPayInventory 证券应收应付库存实体对象
     * @return
     */
    @Override
    public int deleteSecuritiesClosedPayInventory(SecuritiesClosedPayInventory securitiesClosedPayInventory) {
        return assetValuationMapper.deleteSecuritiesClosedPayInventory(securitiesClosedPayInventory);
    }

    /**
     * 查询交易数据，对证券进行清算
     * @param dateTime 业务日期
     * @return
     */
    @Override
    public HashMap selectTransactionData(String dateTime) {
        HashMap ransactionDataMap = new HashMap();
        //查询交易数据，对证券进行清算
        ransactionDataMap.put("p_tableName","(select securitiesId,flag,dateTime,fundId,status,SUM(totalSum*flag) totalSum from transactionData\n" +
                "where to_date(dateTime,'yyyy-MM-dd') <= to_date(dateTime,'yyyy-MM-dd') and transactionDataMode in (1,2,3,4)\n" +
                "  and to_date(dateTime,'yyyy-MM-dd') < to_date(settlementDate,'yyyy-MM-dd') GROUP BY securitiesId,dateTime,fundId,status,flag)");
        ransactionDataMap.put("p_condition","");//传入查询条件
        ransactionDataMap.put("p_pageSize",5);//传入分页显示条数
        ransactionDataMap.put("p_page",1);//传入分页页码
        ransactionDataMap.put("p_count",0);//创建out参数，返回数据总条数
        ransactionDataMap.put("p_cursor",null);//创建out游标变量，返回查询数据
        System.out.println("插入="+ransactionDataMap);
        assetValuationMapper.selectTransactionData(ransactionDataMap);
        return ransactionDataMap;
    }

    /**
     * 删除证券应收应付库存 ，先删后增
     * @param securitiesClosedPayInventory 证券应收应付库存实体对象
     * @return
     */
    @Override
    public int deleteSecuritiesClosedPayInventoryTwo(SecuritiesClosedPayInventory securitiesClosedPayInventory) {
        return assetValuationMapper.deleteSecuritiesClosedPayInventoryTwo(securitiesClosedPayInventory);
    }

    /**
     * 查询TA交易清算款数据
     * @return
     */
    @Override
    public HashMap selectTaTransaction(String fundId,String dateTime) {
        System.out.println("信息="+fundId+dateTime);
        HashMap taTransactionMap = new HashMap();
        //查询TA交易数据，对TA交易进行清算
        taTransactionMap.put("p_tableName","(select sum(totalMoney) totalMoney,transactionType,accountId,dateTime ,fundId from (select * from taTransaction\n" +
                "where fundId='"+fundId+"' and '"+dateTime+"'<BALANCEDATE and '"+dateTime+"'<=DATETIME)\n" +
                " group by transactionType, accountId,fundId,dateTime)");
        taTransactionMap.put("p_condition","");//传入查询条件
        taTransactionMap.put("p_pageSize",5);//传入分页显示条数
        taTransactionMap.put("p_page",1);//传入分页页码
        taTransactionMap.put("p_count",0);//创建out参数，返回数据总条数
        taTransactionMap.put("p_cursor",null);//创建out游标变量，返回查询数据
        assetValuationMapper.selectTaTransaction(taTransactionMap);
        return taTransactionMap;
    }
    /**
     * 删除现金应收应付库存
     * @param cashClosedPayInventory 现金应收应付库存实体对象
     * @return
     */
    @Override
    public int deleteCashClosedPaylnventory(CashClosedPayInventory cashClosedPayInventory){
        return assetValuationMapper.deleteCashClosedPaylnventory(cashClosedPayInventory);
    }
}

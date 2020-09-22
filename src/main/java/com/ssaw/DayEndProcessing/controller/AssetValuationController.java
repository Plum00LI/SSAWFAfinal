package com.ssaw.DayEndProcessing.controller;

import com.ssaw.BusinessData.entity.TransactionData;
import com.ssaw.DayEndProcessing.entity.AssetValuationData;
import com.ssaw.DayEndProcessing.entity.StockSecuritiesJoinMarket;
import com.ssaw.DayEndProcessing.mapper.AssetValuationMapper;
import com.ssaw.DayEndProcessing.service.AssetValuationService;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import com.ssaw.GlobalManagement.util.SysUtil;
import com.ssaw.InventoryManagement.entity.CashClosedPayInventory;
import com.ssaw.InventoryManagement.entity.SecuritiesClosedPayInventory;
import com.ssaw.InventoryManagement.service.CashClosedPayInventoryService;
import com.ssaw.InventoryManagement.service.SecuritiesClosedPayInventoryService;
import com.ssaw.TAManagement.entity.TaTransaction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

/**
 *@program: FA
 *@description: 资产估值控制类
 *@author: 瞿平
 *@create: 2020-09-13 17:28
 **/
@RestController
@RequestMapping("assetValuationController")
public class AssetValuationController {
    //调用资产估值Dao类
    @Resource
    AssetValuationMapper assetValuationMapper;
    //调用资产估值service类
    @Resource
    AssetValuationService assetValuationService;
    //调用证券应收应付库存service类
    @Resource
    SecuritiesClosedPayInventoryService securitiesClosedPayInventoryService;
    //调用现金应收应付库存service类
    @Resource
    CashClosedPayInventoryService cashClosedPayInventoryService;
    //调用工具类
    @Resource
    DbUtil dbUtil;

    /**
     * 资产估值查询方法
     * @return
     */
    @RequestMapping("selectAssetValuationData")
    public HashMap selectAssetValuationData() {
        List<AssetValuationData> assetValuationDataList = assetValuationService.selectBiaoge();
        HashMap assetValuationDataMap = new HashMap();
        assetValuationDataMap.put("code",0);
        assetValuationDataMap.put("msg","");
        assetValuationDataMap.put("count",null);
        assetValuationDataMap.put("data",assetValuationDataList);
        System.out.println(assetValuationDataMap);
        return assetValuationDataMap;
    }

    /**
     * 资产估值操作类
     * @param toDay 业务日期
     * @param arrJson json数据
     * @param fundId 基金Id
     * @return
     */
    @RequestMapping("startValuation")
    public int startValuation(String toDay,String arrJson,String fundId){
        System.out.println("进来了");
        System.out.println(arrJson+" "+toDay);
        int i =0;
        List<AssetValuationData> assetValuationDataList = SysUtil.jsonToArrayList(arrJson, AssetValuationData.class);
        for (AssetValuationData assetValuationData : assetValuationDataList) {

            if(assetValuationData.getAssetValuationType().equals("证券估值增值")){
                System.out.println("证券估值增值开始估值");
                //基金代码  业务日期
                HashMap stockarketMap = assetValuationService.selectStockarket(fundId,toDay);
                System.out.println("stockarketMap="+stockarketMap);
                List<StockSecuritiesJoinMarket> stockSecuritiesJoinMarketList = (List<StockSecuritiesJoinMarket>) stockarketMap.get("p_cursor");
                for (StockSecuritiesJoinMarket stockSecuritiesJoinMarket : stockSecuritiesJoinMarketList) {
                    System.out.println( stockSecuritiesJoinMarket.getSecuritiesId()+"========================================");
                    //将估值的数据添加到证券应收应付库存
                    SecuritiesClosedPayInventory securitiesClosedPayInventory = new SecuritiesClosedPayInventory();
                    //开始执行增加
                    securitiesClosedPayInventory.setFundId(stockSecuritiesJoinMarket.getFundId());
                    securitiesClosedPayInventory.setSecuritiesId(stockSecuritiesJoinMarket.getSecuritiesId());
                    securitiesClosedPayInventory.setSecurityPeriodFlag(stockSecuritiesJoinMarket.getSecurityPeriodFlag());
                    securitiesClosedPayInventory.setSecuritiesClosedPayInventoryId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.SCPI));
                    securitiesClosedPayInventory.setDateTime(toDay);
                    securitiesClosedPayInventory.setSecuritiesType(1);
                    securitiesClosedPayInventory.setFlag(1);
                    securitiesClosedPayInventory.setTotalPrice(stockSecuritiesJoinMarket.getTootaIPrice());
                    securitiesClosedPayInventory.setSecurityPeriodFlag(1);
                    securitiesClosedPayInventory.setSecuritiesClosedPayDesc("投资有风险");
                    System.out.println("增加的实体类="+securitiesClosedPayInventory);
//                        执行删除
                    i = assetValuationService.deleteSecuritiesClosedPayInventory(securitiesClosedPayInventory);
                    //调用增加方法
                    securitiesClosedPayInventoryService.insertSecuritiesClosedPayInventory(securitiesClosedPayInventory);

                }
            }else {
                System.out.println("清算款清算中");
                //查交易数据 按证券代码分组 插入证券应收应付库存
                HashMap hashMap = assetValuationService.selectTransactionData(toDay);
                List<TransactionData> transactionDataList = (List<TransactionData>)hashMap.get("p_cursor");
                for (TransactionData transactionData : transactionDataList) {
                    System.out.println("TransactionData================="+transactionData);
                    SecuritiesClosedPayInventory securitiesClosedPayInventory = new SecuritiesClosedPayInventory();
                    securitiesClosedPayInventory.setSecuritiesClosedPayInventoryId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.SCPI));
                    securitiesClosedPayInventory.setDateTime(toDay);
                    securitiesClosedPayInventory.setFundId(transactionData.getFundId());
                    securitiesClosedPayInventory.setSecuritiesType(2);
                    securitiesClosedPayInventory.setSecuritiesId(transactionData.getSecuritiesId());
                    Double totalSum=0.0;
                    if (transactionData.getFlag()==-1){
                        //资金流向为-1时  总金额为负 拿金额乘以-1得到正确的金额
                        totalSum=transactionData.getTotalSum()*-1;
                    }else{
                        totalSum=transactionData.getTotalSum()*1;
                    }
                    System.out.println("类型="+transactionData.getFlag());
                    securitiesClosedPayInventory.setFlag(transactionData.getFlag());
                    securitiesClosedPayInventory.setTotalPrice(totalSum);
                    securitiesClosedPayInventory.setSecurityPeriodFlag(1);
                    securitiesClosedPayInventory.setSecuritiesClosedPayDesc("投资有风险");
                    System.out.println("查清算款增加的实体类="+securitiesClosedPayInventory);
                    assetValuationService.deleteSecuritiesClosedPayInventoryTwo(securitiesClosedPayInventory);
                    //调用证券应收应付库存增加方法，将清算的数据插入到证券应收应付库存
                    securitiesClosedPayInventoryService.insertSecuritiesClosedPayInventory(securitiesClosedPayInventory);
                    System.out.println("查ta交易数据================================");
                    //查询TA交易数据，进行清算
                    HashMap taTransactionMap = assetValuationService.selectTaTransaction(fundId,toDay);
                    List<TaTransaction> taTransactionList = (List<TaTransaction>)taTransactionMap.get("p_cursor");
                    for (TaTransaction taTransaction : taTransactionList) {
                        System.out.println(taTransaction+"ta==========================================");
                        //new一个现金应收应付库存实体对象
                        CashClosedPayInventory cashClosedPayInventory = new CashClosedPayInventory();
                        cashClosedPayInventory.setCashClosedPayInventoryId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCPI));
                        cashClosedPayInventory.setBusinessDate(taTransaction.getDateTime());
                        cashClosedPayInventory.setFundId(taTransaction.getFundId());
                        cashClosedPayInventory.setAccountId(taTransaction.getAccountId());
                        cashClosedPayInventory.setBusinessType(4);
                        cashClosedPayInventory.setBusinessStatus(1);
                        cashClosedPayInventory.setInitialSigns(1);
                        cashClosedPayInventory.setTotalMoney(taTransaction.getTotalMoney());
                        //调用现金应收应付库存增加方法，请TA清算数据插入到现金应收应付库存
                        i = cashClosedPayInventoryService.insertCashClosedPayInventory(cashClosedPayInventory);
                    }

                }

            }
        }
        return i;
    }
}

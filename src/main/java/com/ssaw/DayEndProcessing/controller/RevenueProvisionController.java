package com.ssaw.DayEndProcessing.controller;

/**
 * 收益计提
 * @type 控制层
 * @author fusaiying
 * @date 2020-09-10
 * @version 1.0
 */
import com.ssaw.BusinessData.entity.CashClosedPay;
import com.ssaw.BusinessData.entity.SecuritiesClosedPay;
import com.ssaw.BusinessData.service.SecuritiesClosedPayService;
import com.ssaw.DayEndProcessing.entity.BondInterest;
import com.ssaw.DayEndProcessing.entity.RevenueProvision;
import com.ssaw.DayEndProcessing.entity.TwoFees;
import com.ssaw.DayEndProcessing.service.RevenueProvisionService;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.OperationType;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import com.ssaw.GlobalManagement.util.SysUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("RevenueProvision")
public class RevenueProvisionController {
    @Resource
    RevenueProvisionService revenueProvisionService;
    @Resource
    com.ssaw.BusinessData.service.CashClosedPayService cashClosedPayService;
    @Resource
    SecuritiesClosedPayService securitiesClosedPayService;
    @Resource
    DbUtil dbUtil;

    //现金的搜索
    @OperLog(message = "收益计提板块现金的查询",operation = OperationType.QUERY)
    @RequestMapping("selectRevenueProvision")
    public HashMap selectRevenueProvision(int page, int limit, String statDate) {
        HashMap hashMap = revenueProvisionService.selectRevenueProvision(page, limit, statDate);
        int count = (int) hashMap.get("p_count");
        List<RevenueProvision> revenueProvisionList = (List<RevenueProvision>) hashMap.get("p_cursor");
        System.out.println(revenueProvisionList);
        HashMap revenueProvisionMap = new HashMap();
        revenueProvisionMap.put("code", 0);
        revenueProvisionMap.put("count", count);
        revenueProvisionMap.put("msg", "");
        revenueProvisionMap.put("data", revenueProvisionList);
        return revenueProvisionMap;
    }

    //债券的搜索
    @OperLog(message = "收益计提板块债券的查询",operation = OperationType.QUERY)
    @RequestMapping("selectBondInterest")
    public HashMap selectBondInterest(int page, int limit, String statDate) {
        System.out.println("第二个" + statDate);
        HashMap hashMap = revenueProvisionService.selectBondInterest(page, limit, statDate);
        int count = (int) hashMap.get("p_count");
        List<BondInterest> bondInterestList = (List<BondInterest>) hashMap.get("p_cursor");
        System.out.println(bondInterestList);
        HashMap bondInterestMap = new HashMap();
        bondInterestMap.put("code", 0);
        bondInterestMap.put("count", count);
        bondInterestMap.put("msg", "");
        bondInterestMap.put("data", bondInterestList);
        return bondInterestMap;
    }

    //两费的搜索
    @OperLog(message = "收益计提板块两费的查询",operation = OperationType.QUERY)
    @RequestMapping("selectTwoFees")
    public HashMap selectTwoFees(int page, int limit, String statDate) {
        HashMap hashMap = revenueProvisionService.selectTwoFees(page, limit, statDate);
        System.out.println(statDate + "=============================");
        int count = (int) hashMap.get("p_count");
        List<TwoFees> twoFeesList = (List<TwoFees>) hashMap.get("p_cursor");
        System.out.println(twoFeesList);
        HashMap twoFeesMap = new HashMap();
        twoFeesMap.put("code", 0);
        twoFeesMap.put("count", count);
        twoFeesMap.put("msg", "");
        twoFeesMap.put("data", twoFeesList);
        return twoFeesMap;
    }

    //现金的统计
    @OperLog(message = "收益计提板块现金的统计",operation = OperationType.ADD+OperationType.DELETE)
    @RequestMapping("CountingCash")
    public int CountingCash(String cash) {
        int i=0;
        System.out.println("进来了===============================================");
        System.out.println(cash);
        List<RevenueProvision> revenueProvisionList = SysUtil.jsonToArrayList(cash, RevenueProvision.class);
        CashClosedPay cashClosedPayPojo = new CashClosedPay();
        for (RevenueProvision revenueProvision : revenueProvisionList) {
            cashClosedPayPojo.setDateTime(revenueProvision.getDateTime());
            cashClosedPayPojo.setFundId(revenueProvision.getFundId());
            cashClosedPayPojo.setAccountId(revenueProvision.getAccountId());
            System.out.println(cashClosedPayPojo+"===============================================");

            String cashClosedPayId = dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCP);
            cashClosedPayPojo.setCashClosedPayId(cashClosedPayId);
            cashClosedPayPojo.setFundId(revenueProvision.getFundId());
            cashClosedPayPojo.setAccountId(revenueProvision.getAccountId());
            cashClosedPayPojo.setServiceType(3);
            cashClosedPayPojo.setAmount(revenueProvision.getInterest());
            System.out.println(revenueProvision.getInterest());
            cashClosedPayPojo.setDateTime(revenueProvision.getDateTime());
            cashClosedPayPojo.setFlag(1);
            i = cashClosedPayService.insertCashClosedPay(cashClosedPayPojo);
            if(i>0){
                i=cashClosedPayService.deleteNew2(cashClosedPayPojo);
                if(i>0){
                    i = cashClosedPayService.insertCashClosedPay(cashClosedPayPojo);
                    System.out.println("这是现金应收应付先删后增的i"+i);
                }
            }
        }
        return i;
    }
//        int i = 0;
//        System.out.println("进来了===============================================");
//        System.out.println(cash);
//        List<RevenueProvision> revenueProvisionList = SysUtil.jsonToArrayList(cash, RevenueProvision.class);
//        CashClosedPay cashClosedPayPojo = new CashClosedPay();
//        for (RevenueProvision revenueProvision : revenueProvisionList) {
//            cashClosedPayPojo.setDateTime(revenueProvision.getDateTime());
//            cashClosedPayPojo.setFundId(revenueProvision.getFundId());
//            cashClosedPayPojo.setAccountId(revenueProvision.getAccountId());
//            System.out.println(cashClosedPayPojo + "===============================================");
//            i = cashClosedPayService.deleteNew2(cashClosedPayPojo);
//            if (i > 0) {
//                String cashClosedPayId = dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCP);
//                cashClosedPayPojo.setCashClosedPayId(cashClosedPayId);
//                cashClosedPayPojo.setFundId(revenueProvision.getFundId());
//                cashClosedPayPojo.setAccountId(revenueProvision.getAccountId());
//                cashClosedPayPojo.setServiceType(3);
//                cashClosedPayPojo.setAmount(revenueProvision.getInterest());
//                System.out.println(revenueProvision.getInterest());
//                cashClosedPayPojo.setDateTime(revenueProvision.getDateTime());
//                cashClosedPayPojo.setFlag(1);
//                i = cashClosedPayService.insertCashClosedPay(cashClosedPayPojo);
//            }
//        }
//        return i;


    //债券的统计
    @OperLog(message = "收益计提板块债券的统计",operation = OperationType.ADD+OperationType.DELETE)
    @RequestMapping("StatisticalSecurities")
    public int StatisticalSecurities(String Securities, String accountId) {
        int i=0;
        System.out.println(Securities);
        System.out.println("进来了==============================================="+accountId);
        List<BondInterest> bondInterestList = SysUtil.jsonToArrayList(Securities, BondInterest.class);
        for (BondInterest bondInterest : bondInterestList) {
            System.out.println(bondInterest.getAccountId()+"这是getAccountId======================================");
            SecuritiesClosedPay securitiesClosedPay1 = new SecuritiesClosedPay();
            String cashClosedPayId = dbUtil.requestDbTableMaxId(SysTableNameListUtil.SCP);
            System.out.println(cashClosedPayId);
            securitiesClosedPay1.setSecuritiesClosedPayId(cashClosedPayId);
            securitiesClosedPay1.setFundId(bondInterest.getFundId());
            securitiesClosedPay1.setAccountId(accountId);
            securitiesClosedPay1.setServiceType(3);
            securitiesClosedPay1.setAmount(bondInterest.getInterest());
            securitiesClosedPay1.setDateTime(bondInterest.getDateTime());
            securitiesClosedPay1.setFlag(1);
            securitiesClosedPay1.setSecuritiesId(bondInterest.getSecuritiesId());
            System.out.println(securitiesClosedPay1);
            i = securitiesClosedPayService.insertSecuritiesClosedPay(securitiesClosedPay1);
            System.out.println(i+"插入证券应收应付的I===========================");
            SecuritiesClosedPay securitiesClosedPay = new SecuritiesClosedPay();
            securitiesClosedPay.setDateTime(bondInterest.getDateTime());
            securitiesClosedPay.setFundId(bondInterest.getFundId());
            securitiesClosedPay.setSecuritiesId(bondInterest.getSecuritiesId());
            System.out.println(securitiesClosedPay);
            i = securitiesClosedPayService.deleteSecuritiesClosedPayByPojo(securitiesClosedPay);
            if(i>0){
                System.out.println(i+"===================================这是一个删除证券应收应付的结果");
                String cashClosedPayId1 = dbUtil.requestDbTableMaxId(SysTableNameListUtil.SCP);
                System.out.println(cashClosedPayId1);
                securitiesClosedPay1.setSecuritiesClosedPayId(cashClosedPayId1);
                securitiesClosedPay1.setFundId(bondInterest.getFundId());
                securitiesClosedPay1.setAccountId(accountId);
                securitiesClosedPay1.setServiceType(3);
                securitiesClosedPay1.setAmount(bondInterest.getInterest());
                securitiesClosedPay1.setDateTime(bondInterest.getDateTime());
                securitiesClosedPay1.setFlag(1);
                securitiesClosedPay1.setSecuritiesId(bondInterest.getSecuritiesId());
                System.out.println(securitiesClosedPay1);
                i = securitiesClosedPayService.insertSecuritiesClosedPay(securitiesClosedPay1);
                System.out.println(i+"插入证券应收应付的I===========================");
            }

        }
        return i;
//        int i = 0;
//        System.out.println("进来了===============================================");
//        System.out.println(Securities);
//        List<BondInterest> bondInterestList = SysUtil.jsonToArrayList(Securities, BondInterest.class);
//        for (BondInterest bondInterest : bondInterestList) {
//            System.out.println(bondInterest.getAccountId());
//            SecuritiesClosedPay securitiesClosedPay = new SecuritiesClosedPay();
//            securitiesClosedPay.setDateTime(bondInterest.getDateTime());
//            securitiesClosedPay.setFundId(bondInterest.getFundId());
//            securitiesClosedPay.setSecuritiesId(bondInterest.getSecuritiesId());
//            securitiesClosedPayService.deleteSecuritiesClosedPayByPojo(securitiesClosedPay);
//            SecuritiesClosedPay securitiesClosedPay1 = new SecuritiesClosedPay();
//            String cashClosedPayId = dbUtil.requestDbTableMaxId(SysTableNameListUtil.SCP);
//            System.out.println(cashClosedPayId);
//            securitiesClosedPay1.setSecuritiesClosedPayId(cashClosedPayId);
//            securitiesClosedPay1.setFundId(bondInterest.getFundId());
//            securitiesClosedPay1.setAccountId(accountId);
//            System.out.println(accountId + "我你啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊啊");
//            securitiesClosedPay1.setServiceType(3);
//            securitiesClosedPay1.setAmount(bondInterest.getInterest());
//            securitiesClosedPay1.setDateTime(bondInterest.getDateTime());
//            securitiesClosedPay1.setFlag(1);
//            securitiesClosedPay1.setSecuritiesId(bondInterest.getSecuritiesId());
//            i = securitiesClosedPayService.insertSecuritiesClosedPay(securitiesClosedPay1);
//            System.out.println(i + "啥子患肢打法");
//        }
//        return i;
    }

    @OperLog(message = "收益计提板块两费的统计",operation = OperationType.ADD+OperationType.DELETE)
    //两费的统计
    @RequestMapping("StatisticalTwoFees")
    public int statisticalTwoFees(String TwoFees) {
        int i = 0;
        System.out.println("进来了===============================================");
        System.out.println(TwoFees);
        List<TwoFees> twoFeesList = SysUtil.jsonToArrayList(TwoFees, TwoFees.class);
        for (TwoFees twoFees : twoFeesList) {
            CashClosedPay cashClosedPayPojo = new CashClosedPay();
            cashClosedPayPojo.setDateTime(twoFees.getValueStatisticsDate());
            cashClosedPayPojo.setFundId(twoFees.getFundId());
            cashClosedPayPojo.setAccountId(twoFees.getAccountId());
            cashClosedPayService.deleteNew2(cashClosedPayPojo);
            String cashClosedPayId = dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCP);
            cashClosedPayPojo.setCashClosedPayId(cashClosedPayId);
            cashClosedPayPojo.setFundId(twoFees.getFundId());
            cashClosedPayPojo.setAccountId(twoFees.getAccountId());
            cashClosedPayPojo.setServiceType(1);
            cashClosedPayPojo.setAmount(twoFees.getCost());
            cashClosedPayPojo.setDateTime(twoFees.getValueStatisticsDate());
            cashClosedPayPojo.setFlag(1);
            i = cashClosedPayService.insertCashClosedPay(cashClosedPayPojo);
            System.out.println(1 + "删除两费的状态=========================");
            if (i > 0) {
                String cashClosedPayId1 = dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCP);
                cashClosedPayPojo.setCashClosedPayId(cashClosedPayId1);
                cashClosedPayPojo.setFundId(twoFees.getFundId());
                cashClosedPayPojo.setAccountId(twoFees.getAccountId());
                cashClosedPayPojo.setServiceType(1);
                cashClosedPayPojo.setAmount(twoFees.getManagementMoney());
                cashClosedPayPojo.setDateTime(twoFees.getValueStatisticsDate());
                cashClosedPayPojo.setFlag(-1);
                i = cashClosedPayService.insertCashClosedPay(cashClosedPayPojo);
                cashClosedPayPojo.setServiceType(2);
                cashClosedPayPojo.setAmount(twoFees.getCustodyMoney());
                System.out.println(twoFees);
                i = cashClosedPayService.insertCashClosedPay(cashClosedPayPojo);
                System.out.println(i + "这是两费的i====================================");
            }
        }
        return i;
    }
}

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
    // int page, int limit,String statDate  page 页码 limit 每页条数  statDate  前端传的日期 查询条件
    public HashMap selectRevenueProvision(int page, int limit, String statDate) {
        HashMap hashMap = revenueProvisionService.selectRevenueProvision(page, limit, statDate);
        // 通过(int)hashMap.get("p_count")获得count条数
        int count = (int)hashMap.get("p_count");
        //通过(List<RevenueProvision>)hashMap.get("p_cursor") 获得RevenueProvision类型的集合
        List<RevenueProvision> revenueProvisionList = (List<RevenueProvision>) hashMap.get("p_cursor");
        System.out.println(revenueProvisionList);
        //返回集合数据到前端
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
        // 通过(int)hashMap.get("p_count")获得count条数
        int count = (int)hashMap.get("p_count");
        //通过(List<BondInterest>)hashMap.get("p_cursor");获得BondInterest类型的集合
        List<BondInterest> bondInterestList = (List<BondInterest>)hashMap.get("p_cursor");
        System.out.println(bondInterestList);
        //返回bondInterestMap 集合到前端
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
        // 通过(int)hashMap.get("p_count")获得count条数
        int count = (int)hashMap.get("p_count");
        //通过(List<TwoFees>)hashMap.get("p_cursor"); 获得TwoFees类型的集合
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
    //String cash,HttpServletRequest request cash是前端返回的json对象字符串
    public int CountingCash(String cash) {
        int i=0;
        System.out.println("进来了===============================================");
        System.out.println(cash);
        //通过调用SysUtil.jsonToArrayList(cash, RevenueProvision.class);工具类得到RevenueProvision类型的集合
        List<RevenueProvision> revenueProvisionList = SysUtil.jsonToArrayList(cash, RevenueProvision.class);
        CashClosedPay cashClosedPayPojo = new CashClosedPay();
        //遍历集合
        for (RevenueProvision revenueProvision : revenueProvisionList) {
            //setDateTime 放时间
            cashClosedPayPojo.setDateTime(revenueProvision.getDateTime());
            //setFundId  放基金Id
            cashClosedPayPojo.setFundId(revenueProvision.getFundId());
            //setAccountId 账户Id
            cashClosedPayPojo.setAccountId(revenueProvision.getAccountId());
            //dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCP) 通过工具类获得最大Id
            String cashClosedPayId = dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCP);
            cashClosedPayPojo.setCashClosedPayId(cashClosedPayId);
            //cashClosedPayPojo.setFundId(revenueProvision.getFundId());
            //cashClosedPayPojo.setAccountId(revenueProvision.getAccountId());
            //setServiceType 类型为3
            cashClosedPayPojo.setServiceType(3);
            //.setAmount 放入利息  getInterest get利息
            cashClosedPayPojo.setAmount(revenueProvision.getInterest());
            System.out.println(revenueProvision.getInterest());
            //cashClosedPayPojo.setDateTime(revenueProvision.getDateTime());
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

    //债券的统计
    @OperLog(message = "收益计提板块债券的统计",operation = OperationType.ADD+OperationType.DELETE)
    @RequestMapping("StatisticalSecurities")
    public int StatisticalSecurities(String Securities,String accountId) {
        int i=0;
        System.out.println(Securities);
        System.out.println("进来了==============================================="+accountId);
        List<BondInterest> bondInterestList = SysUtil.jsonToArrayList(Securities, BondInterest.class);
        for (BondInterest bondInterest : bondInterestList) {
            System.out.println(bondInterest.getAccountId()+"这是getAccountId======================================");
            //创建SecuritiesClosedPay实体类
            SecuritiesClosedPay securitiesClosedPay1 = new SecuritiesClosedPay();
            String cashClosedPayId = dbUtil.requestDbTableMaxId(SysTableNameListUtil.SCP);//获得最大ID
            System.out.println(cashClosedPayId);
            //放入setSecuritiesClosedPayId(cashClosedPayId);
            securitiesClosedPay1.setSecuritiesClosedPayId(cashClosedPayId);
            //setFundId(bondInterest.getFundId());
            securitiesClosedPay1.setFundId(bondInterest.getFundId());
            //securitiesClosedPay1.setAccountId(accountId);
            securitiesClosedPay1.setAccountId(accountId);
            //setServiceType(3);
            securitiesClosedPay1.setServiceType(3);
            //setAmount(bondInterest.getInterest());
            //Amount是现金应收应付表的利息  Interest是债券信息算出的利息
            securitiesClosedPay1.setAmount(bondInterest.getInterest());
            //setDateTime
            securitiesClosedPay1.setDateTime(bondInterest.getDateTime());
            //setFlag(1);
            securitiesClosedPay1.setFlag(1);
            //证券Id
            securitiesClosedPay1.setSecuritiesId(bondInterest.getSecuritiesId());
            System.out.println(securitiesClosedPay1);
            //先增
            i = securitiesClosedPayService.insertSecuritiesClosedPay(securitiesClosedPay1);
            System.out.println(i+"插入证券应收应付的I===========================");

            if(i>0){
                //删除
                i = securitiesClosedPayService.deleteSecuritiesClosedPayByPojo(securitiesClosedPay1);
                if(i>0) {
                    //再增
                    i = securitiesClosedPayService.insertSecuritiesClosedPay(securitiesClosedPay1);
                }
            }
        }
        return i;
    }

    @OperLog(message = "收益计提板块两费的统计",operation = OperationType.ADD+OperationType.DELETE)
    //两费的统计
    @RequestMapping("StatisticalTwoFees")
    //前端返回的json字符串名字TwoFees  String TwoFees
    public int statisticalTwoFees(String TwoFees) {
        int i=0;
        System.out.println("进来了===============================================");
        System.out.println(TwoFees);
        //通过JsonUtil.jsonToArrayList(TwoFees, TwoFees.class); 得到twoFeesList类型的集合
        List<TwoFees> twoFeesList = SysUtil.jsonToArrayList(TwoFees, TwoFees.class);
        for (TwoFees twoFees: twoFeesList) {
            //如果是第一次查询现金应收应付没有数据，先增加
            System.out.println(twoFees+"===================================================");
            CashClosedPay cashClosedPayPojo = new CashClosedPay();
            //dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCP);获得最大ID
            String cashClosedPayId = dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCP);
            //setCashClosedPayId
            cashClosedPayPojo.setCashClosedPayId(cashClosedPayId);
            //setFundId
            cashClosedPayPojo.setFundId(twoFees.getFundId());
            //setAccountId
            cashClosedPayPojo.setAccountId(twoFees.getAccountId());
            //setServiceType(1);
            cashClosedPayPojo.setServiceType(1);
            //setAmount(twoFees.getManagementMoney());  数据库字段名不一样
            cashClosedPayPojo.setAmount(twoFees.getManagementMoney());
            //setDateTime(twoFees.getValueStatisticsDate()); 数据库字段名不一样
            cashClosedPayPojo.setDateTime(twoFees.getValueStatisticsDate());
            cashClosedPayPojo.setFlag(1);

            //增加管理费利息数据
            int i1=cashClosedPayService.insertCashClosedPay(cashClosedPayPojo);
            if (i1>0){
                i1=cashClosedPayService.deleteNew2(cashClosedPayPojo);
                if(i1>0){
                    cashClosedPayService.insertCashClosedPay(cashClosedPayPojo);
                }
            }
            //增加托管费利息数据
            cashClosedPayPojo.setServiceType(2);
            double custodyMoney = twoFees.getCustodyMoney();
            cashClosedPayPojo.setAmount(custodyMoney);
            int i2 = cashClosedPayService.insertCashClosedPay(cashClosedPayPojo);
            if(i2>0){
                i2 = cashClosedPayService.deleteNew2(cashClosedPayPojo);
                if(i2>0){
                    i = cashClosedPayService.insertCashClosedPay(cashClosedPayPojo);
                }
            }
        }
        return i;
    }
}

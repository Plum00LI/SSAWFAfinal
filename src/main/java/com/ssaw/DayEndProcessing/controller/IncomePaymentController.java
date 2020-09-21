package com.ssaw.DayEndProcessing.controller;

import com.ssaw.BusinessData.entity.CashClosedPay;
import com.ssaw.BusinessData.entity.SecuritiesClosedPay;
import com.ssaw.BusinessData.service.CashClosedPayService;
import com.ssaw.BusinessData.service.SecuritiesClosedPayService;
import com.ssaw.CashManagement.entity.BankTreasurer;
import com.ssaw.CashManagement.service.BankTreasurerService;
import com.ssaw.DayEndProcessing.entity.BondInterestIncome;
import com.ssaw.DayEndProcessing.entity.CashInterestIncome;
import com.ssaw.DayEndProcessing.entity.PayTwoFees;
import com.ssaw.DayEndProcessing.service.IncomePaymentService;
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
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 *
 * @ClassName IncomePaymentController
 * @Description: TODO
 * @Author 阙魁
 * @Date 2020/9/9
 * @Version 1.0
 **/
@RestController
@RequestMapping("incomePayment")
public class IncomePaymentController {
    @Resource
    IncomePaymentService incomePaymentService;
    @Resource
    DbUtil dbUtil;
    @Resource
    CashClosedPayService cashClosedPayService;
    @Resource
    BankTreasurerService bankTreasurerService;
    @Resource
    SecuritiesClosedPayService securitiesClosedPayService;

    @OperLog(message = "查询收益支付中的现金利息收入",operation = OperationType.QUERY)
    @RequestMapping("selectCashInterestIncome")
    public Map<String, Object> selectCashInterestIncome(String page, String limit, String statDate, String fundId) {
        System.out.println("进入了现金利息收入查询controller");
        System.out.println("时间："+statDate);
        System.out.println("fundId="+fundId);
        //调用service层，返回结果集map
        Map<String, Object> map = incomePaymentService.selectCashInterestIncome(limit, page,statDate,fundId);
        //从结果集中拿出结果
        //接收返回数据
        List<CashInterestIncome> cashInterestIncomes= (List<CashInterestIncome>) map.get("cashInterestIncomes");

        //接收返回总条数
        int count= (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",cashInterestIncomes);
        json.put("date",statDate);
        System.out.println("控制层:"+cashInterestIncomes.toString());
        //返回数据
        return json;
    }

    @OperLog(message = "查询收益支付中的债券利息收入",operation = OperationType.QUERY)
    @RequestMapping("selectBondInterestIncome")
    public Map<String, Object> selectBondInterestIncome(String page, String limit, String statDate, String fundId) {
        System.out.println("进入了债券利息收入查询controller");
        //调用service层，返回结果集map
        Map<String, Object> map = incomePaymentService.selectBondInterestIncome(limit,page,statDate,fundId);
        //从结果集中拿出结果
        //接收返回数据
        List<BondInterestIncome> bondInterestIncomes= (List<BondInterestIncome>) map.get("bondInterestIncomes");
        //接收返回总条数
        int count= (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",bondInterestIncomes);
        System.out.println("控制层:"+bondInterestIncomes.toString());
        //返回数据
        return json;
    }

    @OperLog(message = "查询收益支付中的两费收入",operation = OperationType.QUERY)
    @RequestMapping("selectPayTwoFees")
    public Map<String, Object> selectPayTwoFees(String page, String limit, String statDate, String fundId) {
        System.out.println("进入了支付两费查询controller");
        //调用service层，返回结果集map
        Map<String, Object> map = incomePaymentService.selectPayTwoFees(limit,page,statDate,fundId);
        //从结果集中拿出结果
        //接收返回数据
        List<PayTwoFees> payTwoFees= (List<PayTwoFees>) map.get("payTwoFees");
        //接收返回总条数
        int count= (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String, Object> json = new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",payTwoFees);
        System.out.println("控制层:"+payTwoFees.toString());
        //返回数据
        return json;
    }


    //现金统计控制类
    @OperLog(message = "统计收益支付中的现金利息收入",operation = OperationType.ADD)
    @RequestMapping("statisticCas")
    public int statisticCas(String cash){
        System.out.println("进入了统计现金Controller...");
        System.out.println(cash);
        int i=0;

        List<CashInterestIncome> cashInterestIncomeList= SysUtil.jsonToArrayList(cash,CashInterestIncome.class);
        CashClosedPay cashClosedPay=new CashClosedPay();
        BankTreasurer bankTreasurer=new BankTreasurer();

        for (CashInterestIncome cashInterestIncome : cashInterestIncomeList) {
            System.out.println(cashInterestIncome);
            //cashClosedPay set方法获得集合时间
            cashClosedPay.setDateTime(cashInterestIncome.getBusinessDate());
            //cashClosedPay set方法获得集合基金Id
            cashClosedPay.setFundId(cashInterestIncome.getFundId());
            //cashClosedPay set方法获得集合业务类型

            cashClosedPay.setServiceType(cashInterestIncome.getBusinessType());
            //cashClosedPay set方法获得集合流入流出状态
            cashClosedPay.setFlag((cashInterestIncome.getBusinessStatus()*-1));

            //调用现金应收应付的service的查询方法查询出现金应收应付编号
            List<CashClosedPay> list=   cashClosedPayService.selectNew(cashClosedPay);

            for (CashClosedPay closedPay : list) {
                System.out.println("业务编号："+closedPay.getCashClosedPayId());
                //调用资金调拨表的service的删除方法
                int b=  bankTreasurerService.deleteBankTreasurerByDepositId(closedPay.getCashClosedPayId());
                System.out.println("资金调拨的删除:"+b);
            }


            //调用现金应收应付表的service的删除方法
            cashClosedPayService.deleteNew(cashClosedPay);

            //----------------------------现金应收应付增加方法--------------------
            String cashClosedPayId = dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCP);
            //cashClosedPay set方法获得现金应收应收Id
            cashClosedPay.setCashClosedPayId(cashClosedPayId);
            //cashClosedPay set方法获得集合基金Id
            cashClosedPay.setFundId(cashInterestIncome.getFundId());
            //cashClosedPay set方法获得集合现金Id
            cashClosedPay.setAccountId(cashInterestIncome.getAccountId());
            //cashClosedPay set方法获得集合业务类型
            cashClosedPay.setServiceType(cashInterestIncome.getBusinessType());
            //cashClosedPay set方法获得集合金额
            cashClosedPay.setAmount(cashInterestIncome.getTotalMoney());
            //cashClosedPay set方法获得集合业务时间
            cashClosedPay.setDateTime(cashInterestIncome.getBusinessDate());
            //cashClosedPay set方法获得集合流入流出
            int strFlag=cashInterestIncome.getBusinessStatus();
            cashClosedPay.setFlag((strFlag*-1));
            i=cashClosedPayService.insertCashClosedPay(cashClosedPay);

            //----------------------资金调拨增加方法--------------------
            //1调用bankTreasurer的 set方法获得调拨编号
            String bankTreasurerId=dbUtil.requestDbTableMaxId(SysTableNameListUtil.BT);
            bankTreasurer.setBankTreasurerId(bankTreasurerId);
            //2调用bankTreasurer的 set方法获得基金Id
            bankTreasurer.setFundId(cashInterestIncome.getFundId());
            //3调用bankTreasurer的 set方法获得调拨总数额
            bankTreasurer.setTotalPrice(cashInterestIncome.getTotalMoney());
            //4调用bankTreasurer的 set方法获得现金账户Id
            bankTreasurer.setAccountId(cashInterestIncome.getAccountId());
            //5调用bankTreasurer的 set方法获得调拨方向flag
            bankTreasurer.setFlag(cashInterestIncome.getBusinessStatus());
            //6调用bankTreasurer的 set方法获得DateTime调拨日期
            bankTreasurer.setDateTime(cashInterestIncome.getBusinessDate());
            //7调用bankTreasurer的 set方法获得DbTime业务日期
            bankTreasurer.setDbTime(cashInterestIncome.getBusinessDate());
            //8调用bankTreasurer的 set方法获得businessId业务标号
            bankTreasurer.setBusinessId(cashClosedPayId);
            //9调用bankTreasurer的 set方法获得allocatingType调拨类型
            //int allocatingType的整数
            int allocatingType=0;
            //判断cashInterestIncome.getBusinessType() 类型为3的话allocatingType=1 为1、2的话allocatingType=6
            if(cashInterestIncome.getBusinessType()==3){
                allocatingType=1;
            }
            bankTreasurer.setAllocatingType(allocatingType);
            //10调用bankTreasurer的 set方法获得bankTreasurerDesc备注
            bankTreasurer.setBankTreasurerDesc("一度教育");
            bankTreasurerService.insertBankTreasurer(bankTreasurer);

        }
        System.out.println("i="+i);
        return i;
    }

    //证券统计控制类
    @OperLog(message = "统计收益支付中的债券利息收入",operation = OperationType.ADD)
    @RequestMapping("statisticSecurties")
    public int statisticSecurties(String Securities,String accountIds){
        System.out.println("进入了统计证券的Controller...");
        System.out.println("Securities="+Securities);
        System.out.println("accountId="+accountIds);
        int i=0;

        List<BondInterestIncome> bondInterestIncomeList= SysUtil.jsonToArrayList(Securities, BondInterestIncome.class);
        SecuritiesClosedPay securitiesClosedPay=new SecuritiesClosedPay();
        BankTreasurer bankTreasurer=new BankTreasurer();

        for (BondInterestIncome bondInterestIncome : bondInterestIncomeList) {
            System.out.println(bondInterestIncome);
            //securitiesClosedPay set方法获得集合时间
            securitiesClosedPay.setDateTime(bondInterestIncome.getDateTime());
            //securitiesClosedPay set方法获得集合基金Id
            securitiesClosedPay.setFundId(bondInterestIncome.getFundId());
            //securitiesClosedPay set方法获得集合业务类型
            securitiesClosedPay.setServiceType(bondInterestIncome.getSecuritiesType());
            //securitiesClosedPay set方法获得集合流入流出状态
            securitiesClosedPay.setFlag((bondInterestIncome.getFlag()*-1));

            //调用证券应收应付的service的查询方法查询出 证券应收应付编号

            List<SecuritiesClosedPay> list=securitiesClosedPayService.selectSecuritiesNew(securitiesClosedPay);

            //调用资金调拨表的service的删除方法
            for (SecuritiesClosedPay closedPay : list) {
                System.out.println("业务编号："+closedPay.getSecuritiesClosedPayId());
                //调用资金调拨表的service的删除方法
                int b=  bankTreasurerService.deleteBankTreasurerByDepositId(closedPay.getSecuritiesClosedPayId());
                System.out.println("资金调拨的删除:"+b);
            }
            //调用现金应收应付表的service的删除方法
            securitiesClosedPayService.deleteSecuritiesNew(securitiesClosedPay);

            //----------------------------现金应收应付增加方法--------------------
            String securitiesClosedPayId = dbUtil.requestDbTableMaxId(SysTableNameListUtil.SCP);
            //securitiesClosedPay set方法获得现金应收应收Id
            securitiesClosedPay.setSecuritiesClosedPayId(securitiesClosedPayId);
            //securitiesClosedPay set方法获得集合基金Id
            securitiesClosedPay.setFundId(bondInterestIncome.getFundId());
            //securitiesClosedPay set方法获得集合现金Id
            securitiesClosedPay.setAccountId(accountIds);
            //securitiesClosedPay set方法获得集合证券信息表ID
            securitiesClosedPay.setSecuritiesId(bondInterestIncome.getSecuritiesId());
            //securitiesClosedPay set方法获得集合业务类型 1=清算款 2=估值增值 3=债券利息
            securitiesClosedPay.setServiceType(bondInterestIncome.getSecuritiesType());
            //securitiesClosedPay set方法获得集合业务金额
            securitiesClosedPay.setAmount(bondInterestIncome.getTotalPrice());
            //securitiesClosedPay set方法获得集合日期
            securitiesClosedPay.setDateTime(bondInterestIncome.getDateTime());
            //securitiesClosedPay set方法获得集合流入流出
            int flag=bondInterestIncome.getFlag();
            securitiesClosedPay.setFlag((flag*-1));
            i=securitiesClosedPayService.insertSecuritiesClosedPay(securitiesClosedPay);

            //----------------------资金调拨增加方法--------------------
            //1调用bankTreasurer的 set方法获得调拨编号
            String bankTreasurerId=dbUtil.requestDbTableMaxId(SysTableNameListUtil.BT);
            bankTreasurer.setBankTreasurerId(bankTreasurerId);
            //2调用bankTreasurer的 set方法获得基金Id
            bankTreasurer.setFundId(bondInterestIncome.getFundId());
            //3调用bankTreasurer的 set方法获得调拨总数额
            bankTreasurer.setTotalPrice(bondInterestIncome.getTotalPrice());
            //4调用bankTreasurer的 set方法获得现金账户Id
            bankTreasurer.setAccountId(accountIds);
            //5调用bankTreasurer的 set方法获得调拨方向flag
            bankTreasurer.setFlag(bondInterestIncome.getFlag());
            //6调用bankTreasurer的 set方法获得dbTime调拨日期
            bankTreasurer.setDbTime(bondInterestIncome.getDateTime());
            //7调用bankTreasurer的 set方法获得dateTime业务日期
            bankTreasurer.setDateTime(bondInterestIncome.getDateTime());
            //8调用bankTreasurer的 set方法获得businessId业务标号
            bankTreasurer.setBusinessId(securitiesClosedPayId);
            //9调用bankTreasurer的 set方法获得allocatingType调拨类型
            //int allocatingType的整数
            int allocatingType=0;
            //判断cashInterestIncome.getBusinessType() 类型为3的话allocatingType=1 为1、2的话allocatingType=6
            if(bondInterestIncome.getSecuritiesType()==3){
                allocatingType=4;
            }
            bankTreasurer.setAllocatingType(allocatingType);
            //10调用bankTreasurer的 set方法获得bankTreasurerDesc备注
            bankTreasurer.setBankTreasurerDesc("一度教育");
            bankTreasurerService.insertBankTreasurer(bankTreasurer);

        }
        return i;

    }

    //两费支付控制类
    @OperLog(message = "统计收益支付中的两费收入",operation = OperationType.ADD)
    @RequestMapping("statisticPay")
    public int statisticPay(String TwoFees){
        System.out.println("进入了两费Controller...");
        System.out.println(TwoFees);
        int i=0;

        List<PayTwoFees> payTwoFeesList= SysUtil.jsonToArrayList(TwoFees, PayTwoFees.class);
        CashClosedPay cashClosedPay=new CashClosedPay();
        BankTreasurer bankTreasurer=new BankTreasurer();

        for (PayTwoFees payTwoFees : payTwoFeesList) {
            System.out.println(payTwoFees);
            //cashClosedPay set方法获得集合时间
            cashClosedPay.setDateTime(payTwoFees.getBusinessDate());
            //cashClosedPay set方法获得集合基金Id
            cashClosedPay.setFundId(payTwoFees.getFundId());
            //cashClosedPay set方法获得集合业务类型
            cashClosedPay.setServiceType(payTwoFees.getBusinessType());
            //cashClosedPay set方法获得集合流入流出状态
            cashClosedPay.setFlag((payTwoFees.getBusinessStatus()*-1));

            //调用现金应收应付的service的查询方法查询出现金应收应付编号
            cashClosedPayService.selectNew(cashClosedPay);

            //调用现金应收应付的service的查询方法查询出现金应收应付编号
            List<CashClosedPay> list=   cashClosedPayService.selectNew(cashClosedPay);

            for (CashClosedPay closedPay : list) {
                System.out.println("业务编号："+closedPay.getCashClosedPayId());
                //调用资金调拨表的service的删除方法
                int b=  bankTreasurerService.deleteBankTreasurerByDepositId(closedPay.getCashClosedPayId());
                System.out.println("资金调拨的删除:"+b);
            }

            //调用现金应收应付表的service的删除方法
            cashClosedPayService.deleteNew(cashClosedPay);

            //----------------------------现金应收应付增加方法--------------------
            String cashClosedPayId = dbUtil.requestDbTableMaxId(SysTableNameListUtil.CCP);
            //cashClosedPay set方法获得现金应收应收Id
            cashClosedPay.setCashClosedPayId(cashClosedPayId);
            //cashClosedPay set方法获得集合基金Id
            cashClosedPay.setFundId(payTwoFees.getFundId());
            //cashClosedPay set方法获得集合现金Id
            cashClosedPay.setAccountId(payTwoFees.getAccountId());
            //cashClosedPay set方法获得集合业务类型
            cashClosedPay.setServiceType(payTwoFees.getBusinessType());
            //cashClosedPay set方法获得集合金额
            cashClosedPay.setAmount(payTwoFees.getTotalMoney());
            //cashClosedPay set方法获得集合业务时间
            cashClosedPay.setDateTime(payTwoFees.getBusinessDate());
            //cashClosedPay set方法获得集合流入流出
            int strFlag=payTwoFees.getBusinessStatus();
            cashClosedPay.setFlag((strFlag*-1));
            i=cashClosedPayService.insertCashClosedPay(cashClosedPay);
            //----------------------资金调拨增加方法--------------------
            //1调用bankTreasurer的 set方法获得调拨编号
            String bankTreasurerId=dbUtil.requestDbTableMaxId(SysTableNameListUtil.BT);
            bankTreasurer.setBankTreasurerId(bankTreasurerId);
            //2调用bankTreasurer的 set方法获得基金Id
            bankTreasurer.setFundId(payTwoFees.getFundId());
            //3调用bankTreasurer的 set方法获得调拨总数额
            bankTreasurer.setTotalPrice(payTwoFees.getTotalMoney());
            //4调用bankTreasurer的 set方法获得现金账户Id
            bankTreasurer.setAccountId(payTwoFees.getAccountId());
            //5调用bankTreasurer的 set方法获得调拨方向flag
            bankTreasurer.setFlag(payTwoFees.getBusinessStatus());
            //6调用bankTreasurer的 set方法获得dbTime调拨日期
            bankTreasurer.setDbTime(payTwoFees.getBusinessDate());
            //7调用bankTreasurer的 set方法获得dateTime业务日期
            bankTreasurer.setDateTime(payTwoFees.getBusinessDate());
            //8调用bankTreasurer的 set方法获得businessId业务标号
            bankTreasurer.setBusinessId(cashClosedPayId);
            //9调用bankTreasurer的 set方法获得allocatingType调拨类型
            //int allocatingType的整数
            int allocatingType=0;
            //判断cashInterestIncome.getBusinessType() 类型为3的话allocatingType=1 为1、2的话allocatingType=6
            if(payTwoFees.getBusinessType()==1||payTwoFees.getBusinessType()==2){
                allocatingType=6;
            }
            bankTreasurer.setAllocatingType(allocatingType);
            //10调用bankTreasurer的 set方法获得bankTreasurerDesc备注
            bankTreasurer.setBankTreasurerDesc("一度教育");
            bankTreasurerService.insertBankTreasurer(bankTreasurer);

        }
        return i;
    }


}
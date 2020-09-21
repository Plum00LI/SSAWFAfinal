package com.ssaw.DayEndProcessing.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
 * 收益支付 的dao层
 * @type:IncomePayment
 * @version v1.0
 * @author:阙魁
 * @create:2020-09-09
 */
@Mapper
public interface IncomePaymentMapper {
    //现金利息收入查询
    public void selectCashInterestIncome(Map map);
    //债券利息收入查询
    public void selectBondInterestIncome(Map map);
    //支付两费查询
    public void selectPayTwoFees(Map map);
}

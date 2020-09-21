package com.ssaw.DayEndProcessing.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface RevenueProvisionMapper {
    public void selectRevenueProvision(HashMap hashMap);
    public void selectBondInterest(HashMap hashMap);
    public void selectTwoFees(HashMap hashMap);
}

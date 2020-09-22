package com.ssaw.DayEndProcessing.mapper;
/**
 * 收益计提
 * @type dao层
 * @author fusaiying
 * @date 2020-09-10
 * @version 1.0
 */
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface RevenueProvisionMapper {
    public void selectRevenueProvision(HashMap hashMap);
    public void selectBondInterest(HashMap hashMap);
    public void selectTwoFees(HashMap hashMap);
}

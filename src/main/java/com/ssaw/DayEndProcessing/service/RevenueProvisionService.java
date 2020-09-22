package com.ssaw.DayEndProcessing.service;

import java.util.HashMap;
/**
 * 收益计提
 * @type 服务类
 * @author fusaiying
 * @date 2020-09-10
 * @version 1.0
 */
public interface RevenueProvisionService {
    public HashMap selectRevenueProvision(int page, int limit,String statDate);
    public HashMap selectBondInterest(int page, int limit ,String statDate);
    public HashMap selectTwoFees(int page, int limit ,String statDate);
}

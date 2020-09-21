package com.ssaw.DayEndProcessing.service;

import java.util.HashMap;

public interface RevenueProvisionService {
    public HashMap selectRevenueProvision(int page, int limit,String statDate);
    public HashMap selectBondInterest(int page, int limit ,String statDate);
    public HashMap selectTwoFees(int page, int limit ,String statDate);
}

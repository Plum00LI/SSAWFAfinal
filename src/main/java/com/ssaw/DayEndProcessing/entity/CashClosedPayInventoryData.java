package com.ssaw.DayEndProcessing.entity;

/**
 * ClassName:    CashClosedPayInventoryData
 * Package:    com.ssaw.DayEndProcessing.entity
 * Description: 现金应收应付库存
 * Version:
 * Datetime:    2020/9/12   9:21
 * Author:   SYT
 */

public class CashClosedPayInventoryData {
    private int businessType;
    private double casheTotal;
    private int flag;
    private String accountId;

    public CashClosedPayInventoryData(){

    }

    public CashClosedPayInventoryData(int businessType, double casheTotal, int flag, String accountId) {
        this.businessType = businessType;
        this.casheTotal = casheTotal;
        this.flag = flag;
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "CashClosedPayInventoryData{" +
                "businessType=" + businessType +
                ", casheTotal=" + casheTotal +
                ", flag=" + flag +
                ", accountId='" + accountId + '\'' +
                '}';
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public double getCasheTotal() {
        return casheTotal;
    }

    public void setCasheTotal(double casheTotal) {
        this.casheTotal = casheTotal;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}

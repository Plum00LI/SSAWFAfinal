package com.ssaw.DayEndProcessing.entity;

/**
 * ClassName:    CashInventoryData
 * Package:    com.ssaw.DayEndProcessing.entity
 * Description: 保存现金库存查询信息
 * Version:
 * Datetime:    2020/9/11   14:54
 * Author:   SYT
 */
public class CashInventoryData {
    private double cashTotal;
    private String accountId;
    public CashInventoryData(){

    }

    public CashInventoryData(double cashTotal, String accountId) {
        this.cashTotal = cashTotal;
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "CashInventoryData{" +
                "cashTotal=" + cashTotal +
                ", accountId='" + accountId + '\'' +
                '}';
    }

    public double getCashTotal() {
        return cashTotal;
    }

    public void setCashTotal(double cashTotal) {
        this.cashTotal = cashTotal;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}

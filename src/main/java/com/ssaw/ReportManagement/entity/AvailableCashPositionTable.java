package com.ssaw.ReportManagement.entity;

/**
 * ClassName:    AvailableCashPositionTable
 * Package:    com.ssaw.ReportManagement.entity
 * Description: 可用现金头寸表
 * Version:     1.0
 * Datetime:    2020/9/18 14:51
 * Author:   阙魁
 */
public class AvailableCashPositionTable {
    private String accountName;
    private double cashBlance;

    public AvailableCashPositionTable() {
    }

    public AvailableCashPositionTable(String accountName, double cashBlance) {
        this.accountName = accountName;
        this.cashBlance = cashBlance;
    }

    @Override
    public String toString() {
        return "AvailableCashPositionTable{" +
                "accountName='" + accountName + '\'' +
                ", cashBlance=" + cashBlance +
                '}';
    }

    public double getCashBlance() {
        return cashBlance;
    }

    public void setCashBlance(double cashBlance) {
        this.cashBlance = cashBlance;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }



}

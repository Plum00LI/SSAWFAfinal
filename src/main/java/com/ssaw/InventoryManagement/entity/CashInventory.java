package com.ssaw.InventoryManagement.entity;

/**
 * ClassName:    CashInventory
 * Package:    com.ssaw.InventoryManagement.entity
 * Description:
 * Version:   1.0
 * Datetime:    2020/9/1   15:37
 * Author:   SYT
 */
public class CashInventory {
    //主键现金库存 C202008310001
    private String cashInventoryId;
    //FK 基金Id来自基金表
    private String fundId;
    //现金余额
    private double cashBlance;
    //FK 现金账户Id来自现金账户表
    private String accountId;
    //统计日期
    private String dateTime;
    //证券数量
    private int securitiesNum;
    //是否从其他系统导入的期初数据  0：不是  1：是
    private int securityPeriodFlag;
    //备注
    private String cashInventoryDesc;

    private String accountName;


    public CashInventory(){

    }

    /**
     * CashInventory全参构造
     * @param cashInventoryId
     * @param fundId
     * @param cashBlance
     * @param accountId
     * @param dateTime
     * @param securitiesNum
     * @param securityPeriodFlag
     * @param cashInventoryDesc
     */
    public CashInventory(String cashInventoryId, String fundId, double cashBlance, String accountId, String dateTime, int securitiesNum, int securityPeriodFlag, String cashInventoryDesc) {
        this.cashInventoryId = cashInventoryId;
        this.fundId = fundId;
        this.cashBlance = cashBlance;
        this.accountId = accountId;
        this.dateTime = dateTime;
        this.securitiesNum = securitiesNum;
        this.securityPeriodFlag = securityPeriodFlag;
        this.cashInventoryDesc = cashInventoryDesc;
    }

    public CashInventory(String cashInventoryId, String fundId, double cashBlance, String accountId, String dateTime, int securitiesNum, int securityPeriodFlag, String cashInventoryDesc, String accountName) {
        this.cashInventoryId = cashInventoryId;
        this.fundId = fundId;
        this.cashBlance = cashBlance;
        this.accountId = accountId;
        this.dateTime = dateTime;
        this.securitiesNum = securitiesNum;
        this.securityPeriodFlag = securityPeriodFlag;
        this.cashInventoryDesc = cashInventoryDesc;
        this.accountName = accountName;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return "CashInventory{" +
                "cashInventoryId='" + cashInventoryId + '\'' +
                ", fundId='" + fundId + '\'' +
                ", cashBlance=" + cashBlance +
                ", accountId='" + accountId + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", securitiesNum=" + securitiesNum +
                ", securityPeriodFlag=" + securityPeriodFlag +
                ", cashInventoryDesc='" + cashInventoryDesc + '\'' +
                '}';
    }

    public String getCashInventoryId() {
        return cashInventoryId;
    }

    public void setCashInventoryId(String cashInventoryId) {
        this.cashInventoryId = cashInventoryId;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public double getCashBlance() {
        return cashBlance;
    }

    public void setCashBlance(double cashBlance) {
        this.cashBlance = cashBlance;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getSecuritiesNum() {
        return securitiesNum;
    }

    public void setSecuritiesNum(int securitiesNum) {
        this.securitiesNum = securitiesNum;
    }

    public int getSecurityPeriodFlag() {
        return securityPeriodFlag;
    }

    public void setSecurityPeriodFlag(int securityPeriodFlag) {
        this.securityPeriodFlag = securityPeriodFlag;
    }

    public String getCashInventoryDesc() {
        return cashInventoryDesc;
    }

    public void setCashInventoryDesc(String cashInventoryDesc) {
        this.cashInventoryDesc = cashInventoryDesc;
    }
}

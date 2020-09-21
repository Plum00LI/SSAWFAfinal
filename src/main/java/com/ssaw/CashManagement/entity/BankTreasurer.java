package com.ssaw.CashManagement.entity;

/**
*@program:TescComment
*@Eescription:资金调拨实体类
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
public class BankTreasurer {
    private String bankTreasurerId;//资金调拨Id wu
    private String fundId;//基金编号 wu
    private double totalPrice;//调拨总数额
    private String accountId;//现金账户Id
    private int flag;//调拨方向1代表流入-1代表流出
    private String dbTime;//调拨日期
    private String dateTime;//业务日期 wu
    private String businessId;//业务标号
    private int allocatingType;//调拨类型
    private String bankTreasurerDesc;//备注
    private String accountName;
    public BankTreasurer() {
    }

    public BankTreasurer(String bankTreasurerId, String fundId, double totalPrice, String accountId, int flag, String dbTime, String dateTime, String businessId, int allocatingType, String bankTreasurerDesc, String accountName) {
        this.bankTreasurerId = bankTreasurerId;
        this.fundId = fundId;
        this.totalPrice = totalPrice;
        this.accountId = accountId;
        this.flag = flag;
        this.dbTime = dbTime;
        this.dateTime = dateTime;
        this.businessId = businessId;
        this.allocatingType = allocatingType;
        this.bankTreasurerDesc = bankTreasurerDesc;
        this.accountName = accountName;
    }

    public BankTreasurer(String bankTreasurerId, String fundId, double totalPrice, String accountId, int flag, String dbTime, String dateTime, String businessId, int allocatingType, String bankTreasurerDesc) {
        this.bankTreasurerId = bankTreasurerId;
        this.fundId = fundId;
        this.totalPrice = totalPrice;
        this.accountId = accountId;
        this.flag = flag;
        this.dbTime = dbTime;
        this.dateTime = dateTime;
        this.businessId = businessId;
        this.allocatingType = allocatingType;
        this.bankTreasurerDesc = bankTreasurerDesc;
    }

    public String getBankTreasurerId() {
        return bankTreasurerId;
    }

    public void setBankTreasurerId(String bankTreasurerId) {
        this.bankTreasurerId = bankTreasurerId;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDbTime() {
        return dbTime;
    }

    public void setDbTime(String dbTime) {
        this.dbTime = dbTime;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public int getAllocatingType() {
        return allocatingType;
    }

    public void setAllocatingType(int allocatingType) {
        this.allocatingType = allocatingType;
    }

    public String getBankTreasurerDesc() {
        return bankTreasurerDesc;
    }

    public void setBankTreasurerDesc(String bankTreasurerDesc) {
        this.bankTreasurerDesc = bankTreasurerDesc;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    @Override
    public String toString() {
        return "BankTreasurer{" +
                "bankTreasurerId='" + bankTreasurerId + '\'' +
                ", fundId='" + fundId + '\'' +
                ", totalPrice=" + totalPrice +
                ", accountId='" + accountId + '\'' +
                ", flag=" + flag +
                ", dbTime='" + dbTime + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", businessId='" + businessId + '\'' +
                ", allocatingType=" + allocatingType +
                ", bankTreasurerDesc='" + bankTreasurerDesc + '\'' +
                ", accountName='" + accountName + '\'' +
                '}';
    }
}

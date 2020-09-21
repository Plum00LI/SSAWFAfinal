package com.ssaw.BusinessDescription.entity;

/**
*@program:TescComment
*@Eescription:现金账户实体类
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
public class Account {
    private String accountId;//现金账户id
    private String fundId;//基金Id
    private String blankCardCode;//银行卡号
    private String accountName;//账户名称
    private String blankName;//银行名称
    private int deposit;//存款类型1代表活期2代表定期
    private double cardRate;//卡号利率
    private int procisionDays;//计息时间 1代表3602代表365天3代表366天
    private String openTime;//开户时间
    private String endTime;//结束时间
    private String accountDesc;//备注

    public Account() {
    }
    public Account(String accountId, String fundId, String blankCardCode, String accountName, String blankName, int deposit, double cardRate, int procisionDays, String openTime, String endTime, String accountDesc) {
        this.accountId = accountId;
        this.fundId = fundId;
        this.blankCardCode = blankCardCode;
        this.accountName = accountName;
        this.blankName = blankName;
        this.deposit = deposit;
        this.cardRate = cardRate;
        this.procisionDays = procisionDays;
        this.openTime = openTime;
        this.endTime = endTime;
        this.accountDesc = accountDesc;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getBlankCardCode() {
        return blankCardCode;
    }

    public void setBlankCardCode(String blankCardCode) {
        this.blankCardCode = blankCardCode;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBlankName() {
        return blankName;
    }

    public void setBlankName(String blankName) {
        this.blankName = blankName;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }

    public double getCardRate() {
        return cardRate;
    }

    public void setCardRate(double cardRate) {
        this.cardRate = cardRate;
    }

    public int getProcisionDays() {
        return procisionDays;
    }

    public void setProcisionDays(int procisionDays) {
        this.procisionDays = procisionDays;
    }

    public String getOpenTime() {
        return openTime;
    }

    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getAccountDesc() {
        return accountDesc;
    }

    public void setAccountDesc(String accountDesc) {
        this.accountDesc = accountDesc;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", fundId='" + fundId + '\'' +
                ", blankCardCode='" + blankCardCode + '\'' +
                ", accountName='" + accountName + '\'' +
                ", blankName='" + blankName + '\'' +
                ", deposit=" + deposit +
                ", cardRate=" + cardRate +
                ", procisionDays=" + procisionDays +
                ", openTime='" + openTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", accountDesc='" + accountDesc + '\'' +
                '}';
    }
}

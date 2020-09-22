package com.ssaw.DayEndProcessing.entity;
/**
 * 收益计提-债券
 * @type 实体类
 * @author fusaiying
 * @date 2020-09-10
 * @version 1.0
 */
public class RevenueProvision {
    private String accountId; //账户 Id
    private String fundId;   //基金Id
    private String blankCardCode; //银行卡号
    private String accountName;  //账户名称
    private String blankName;  //银行名称
    private int deposit;  //存款类型Id
    private double cardRate; //年利率
    private int procisionDays;  //计息期间Id
    private String openTime; //开户时间
    private String endTime;  //结束时间
    private String accountDesc; //账户备注
    private Double cashBlance;  //账户余额
    private Double interest; //利息
    private String businessDate; //业务日期
    private String depositName;//存款类型名字
    private String dateTime; //统计日期
    private int procisionDayName;//计息期间天数

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

    public Double getCashBlance() {
        return cashBlance;
    }

    public void setCashBlance(Double cashBlance) {
        this.cashBlance = cashBlance;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getDepositName() {
        return depositName;
    }

    public void setDepositName(String depositName) {
        this.depositName = depositName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getProcisionDayName() {
        return procisionDayName;
    }

    public void setProcisionDayName(int procisionDayName) {
        this.procisionDayName = procisionDayName;
    }

    public RevenueProvision() {
    }

    public RevenueProvision(String accountId, String fundId, String blankCardCode, String accountName, String blankName, int deposit, double cardRate, int procisionDays, String openTime, String endTime, String accountDesc, Double cashBlance, Double interest, String businessDate, String depositName, String dateTime, int procisionDayName) {
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
        this.cashBlance = cashBlance;
        this.interest = interest;
        this.businessDate = businessDate;
        this.depositName = depositName;
        this.dateTime = dateTime;
        this.procisionDayName = procisionDayName;
    }

    @Override
    public String toString() {
        return "RevenueProvision{" +
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
                ", cashBlance=" + cashBlance +
                ", interest=" + interest +
                ", businessDate='" + businessDate + '\'' +
                ", depositName='" + depositName + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", procisionDayName=" + procisionDayName +
                '}';
    }
}

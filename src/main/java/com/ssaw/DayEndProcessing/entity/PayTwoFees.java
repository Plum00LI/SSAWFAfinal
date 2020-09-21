package com.ssaw.DayEndProcessing.entity;

/**
 * 收益支付   支付两费
 * @type:PayTwoFees
 * @version v1.0
 * @author:阙魁
 * @create:2020-09-09
 */
public class PayTwoFees {
    private String accountName; //账号名称
    private String accountId;
    private String fundId;
    private int businessType; //业务类型
    private double totalMoney;//金额
    private int businessStatus; //流入流出
    private String businessDate;    //业务时间

    public PayTwoFees(String accountName, int businessType, double totalMoney, int businessStatus, String businessDate) {
        this.accountName = accountName;
        this.businessType = businessType;
        this.totalMoney = totalMoney;
        this.businessStatus = businessStatus;
        this.businessDate = businessDate;
    }

    public PayTwoFees() {
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

    @Override
    public String toString() {
        return "PayTwoFees{" +
                "accountName='" + accountName + '\'' +
                ", businessType=" + businessType +
                ", totalMoney=" + totalMoney +
                ", businessStatus=" + businessStatus +
                ", businessDate='" + businessDate + '\'' +
                '}';
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(int businessStatus) {
        this.businessStatus = businessStatus;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }
}

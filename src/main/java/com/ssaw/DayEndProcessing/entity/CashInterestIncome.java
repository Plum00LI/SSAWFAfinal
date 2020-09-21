package com.ssaw.DayEndProcessing.entity;

/**
 * 收益支付   现金利息收入
 * @type:CashInterestIncome
 * @version v1.0
 * @author:阙魁
 * @create:2020-09-09
 */
public class CashInterestIncome {

    private String accountName; //账号名称
    private String accountId;
    private String fundId;
    private int businessType; //业务类型
    private int businessStatus; //流入流出
    private String businessDate;    //业务时间
    private double totalMoney;//金额
    private int deposit;//存款类型

    public CashInterestIncome(String accountName, int businessType, int businessStatus, String businessDate, double totalMoney, int deposit) {
        this.accountName = accountName;
        this.businessType = businessType;
        this.businessStatus = businessStatus;
        this.businessDate = businessDate;
        this.totalMoney = totalMoney;
        this.deposit = deposit;
    }

    public CashInterestIncome() {
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
        return "CashInterestIncome{" +
                "accountName='" + accountName + '\'' +
                ", businessType=" + businessType +
                ", businessStatus=" + businessStatus +
                ", businessDate='" + businessDate + '\'' +
                ", totalMoney=" + totalMoney +
                ", deposit=" + deposit +
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

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public int getDeposit() {
        return deposit;
    }

    public void setDeposit(int deposit) {
        this.deposit = deposit;
    }
}

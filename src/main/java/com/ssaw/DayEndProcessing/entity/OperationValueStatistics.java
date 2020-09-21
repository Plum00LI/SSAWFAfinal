package com.ssaw.DayEndProcessing.entity;
/**
 * create by: 佘高鹏
 * description: TODO
 * 现金模块实体类
 * create time: 2020/9/13 18:41
 * version number 1.0
  * @Param: null
 * @return
 */

/**
 * ACCOUNTNAME
 * BLANKCARDCODE
 * CASHBLANCE
 * TOTALMONEY
 */
public class OperationValueStatistics {
    private String accountId;
    private String accountName;
    private String blankCardCode;
    private Double cashBlance;
    private Double totalMoney;

    public OperationValueStatistics() {
    }

    public OperationValueStatistics(String accountId, Double totalMoney) {
        this.accountId = accountId;
        this.totalMoney = totalMoney;
    }

    public OperationValueStatistics(String accountId, String accountName, String blankCardCode, Double cashBlance, Double totalMoney) {
        this.accountId = accountId;
        this.accountName = accountName;
        this.blankCardCode = blankCardCode;
        this.cashBlance = cashBlance;
        this.totalMoney = totalMoney;
    }

    public OperationValueStatistics(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public OperationValueStatistics(String accountName, String blankCardCode, Double cashBlance, Double totalMoney) {
        this.accountName = accountName;
        this.blankCardCode = blankCardCode;
        this.cashBlance = cashBlance;
        this.totalMoney = totalMoney;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBlankCardCode() {
        return blankCardCode;
    }

    public void setBlankCardCode(String blankCardCode) {
        this.blankCardCode = blankCardCode;
    }

    public Double getCashBlance() {
        return cashBlance;
    }

    public void setCashBlance(Double cashBlance) {
        this.cashBlance = cashBlance;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "OperationValueStatistics{" +
                "accountId='" + accountId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", blankCardCode='" + blankCardCode + '\'' +
                ", cashBlance=" + cashBlance +
                ", totalMoney=" + totalMoney +
                '}';
    }
}



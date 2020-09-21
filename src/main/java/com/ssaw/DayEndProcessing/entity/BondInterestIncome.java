package com.ssaw.DayEndProcessing.entity;

/**
 * 收益支付   债券利息收入
 * @type:BondInterestIncome
 * @version v1.0
 * @author:阙魁
 * @create:2020-09-09
 */
public class BondInterestIncome {

    private String accountName; //账号名称
    private String accountId;
    private String fundId;
    private String securitiesId;
    private String securitiesName;//证券名称
    private int securitiesType;////证券应收应付类型 3=债券利息
    private int flag;//业务状态 1流入，-1流出
    private double totalPrice;    //总金额*
    private String dateTime;    //业务日期

    public BondInterestIncome(String accountName, String securitiesName, int securitiesType, int flag, double totalPrice, String dateTime) {
        this.accountName = accountName;
        this.securitiesName = securitiesName;
        this.securitiesType = securitiesType;
        this.flag = flag;
        this.totalPrice = totalPrice;
        this.dateTime = dateTime;
    }

    public BondInterestIncome() {
    }

    public String getSecuritiesId() {
        return securitiesId;
    }

    public void setSecuritiesId(String securitiesId) {
        this.securitiesId = securitiesId;
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
        return "BondInterestIncome{" +
                "accountName='" + accountName + '\'' +
                ", securitiesName='" + securitiesName + '\'' +
                ", securitiesType=" + securitiesType +
                ", flag=" + flag +
                ", totalPrice=" + totalPrice +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSecuritiesName() {
        return securitiesName;
    }

    public void setSecuritiesName(String securitiesName) {
        this.securitiesName = securitiesName;
    }

    public int getSecuritiesType() {
        return securitiesType;
    }

    public void setSecuritiesType(int securitiesType) {
        this.securitiesType = securitiesType;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}

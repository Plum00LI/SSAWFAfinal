package com.ssaw.TAManagement.entity;
/**
 * TA交易数据实体类
 * @type:Tatransaction
 * @version:v1.0
 * @authod:洪彬峰
 * @date:2020-09-01
 */
public class TaTransaction {
    private String taTransactionId;      //PK , 交易数据编号 TA202008310001
    private String dateTime;           //交易日期
    private String balanceDate;        //结算日期
    private String fundId;             //FK 基金Id来自基金表
    private double fundNum;            //交易数量
    private String accountId;          //FK 来自现金账户表 现金账户Id
    private double totalMoney;         //总金额
    private double actualMoney;        //实际交易金额
    private double price;              //单价(昨日单位净值)
    private double cost;               //费用
    private int    agencies;           //代销机构1=建设银行  2=工商银行  3=农业银行
    private int    transactionType;    //1认购 2申购 3赎回
    private int    transactionStatus;  //1结算 0未结算

    public TaTransaction(){}

    public String getTaTransactionId() {
        return taTransactionId;
    }

    public void setTaTransactionId(String taTransactionId) {
        this.taTransactionId = taTransactionId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getBalanceDate() {
        return balanceDate;
    }

    public void setBalanceDate(String balanceDate) {
        this.balanceDate = balanceDate;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public double getFundNum() {
        return fundNum;
    }

    public void setFundNum(double fundNum) {
        this.fundNum = fundNum;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public double getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(double actualMoney) {
        this.actualMoney = actualMoney;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getAgencies() {
        return agencies;
    }

    public void setAgencies(int agencies) {
        this.agencies = agencies;
    }

    public int getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(int transactionType) {
        this.transactionType = transactionType;
    }

    public int getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(int transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    @Override
    public String toString() {
        return "TaTransaction{" +
                "taTransactionId='" + taTransactionId + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", balanceDate='" + balanceDate + '\'' +
                ", fundId='" + fundId + '\'' +
                ", fundNum=" + fundNum +
                ", accountId='" + accountId + '\'' +
                ", totalMoney=" + totalMoney +
                ", actualMoney=" + actualMoney +
                ", price=" + price +
                ", cost=" + cost +
                ", agencies=" + agencies +
                ", transactionType=" + transactionType +
                ", transactionStatus=" + transactionStatus +
                '}';
    }
}

package com.ssaw.ReportManagement.entity;

/**
 * @program:TescComment
 * @Eescription:成交清算轧差实体类
 * @author:黄庆
 * @Version:1.0
 * @create:2020-09-01
 */
public class DifferenceReport {
    private String securitiesName;//证券名称
    private Double outMoney;//今日流出金额
    private Double inMoney;//今日流入金额
    private Double totalSum;//今日清算额
    private Double commission; //佣金费用（券商）
    private Double transfer;        //过户费（交易所）
    private Double brokerage;        //经手费（交易所）
    private Double stamp;            //印花税（上交国家的税）
    private Double management;        //征管费（上交国家的税）
    private Double security;        //证券利息
    private Double netReceipts;//实际清算额

    public DifferenceReport() {
    }

    public DifferenceReport(String securitiesName, Double outMoney, Double inMoney, Double totalSum, Double commission, Double transfer, Double brokerage, Double stamp, Double management, Double security, Double netReceipts) {
        this.securitiesName = securitiesName;
        this.outMoney = outMoney;
        this.inMoney = inMoney;
        this.totalSum = totalSum;
        this.commission = commission;
        this.transfer = transfer;
        this.brokerage = brokerage;
        this.stamp = stamp;
        this.management = management;
        this.security = security;
        this.netReceipts = netReceipts;
    }

    public String getSecuritiesName() {
        return securitiesName;
    }

    public void setSecuritiesName(String securitiesName) {
        this.securitiesName = securitiesName;
    }

    public Double getOutMoney() {
        return outMoney;
    }

    public void setOutMoney(Double outMoney) {
        this.outMoney = outMoney;
    }

    public Double getInMoney() {
        return inMoney;
    }

    public void setInMoney(Double inMoney) {
        this.inMoney = inMoney;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getTransfer() {
        return transfer;
    }

    public void setTransfer(Double transfer) {
        this.transfer = transfer;
    }

    public Double getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(Double brokerage) {
        this.brokerage = brokerage;
    }

    public Double getStamp() {
        return stamp;
    }

    public void setStamp(Double stamp) {
        this.stamp = stamp;
    }

    public Double getManagement() {
        return management;
    }

    public void setManagement(Double management) {
        this.management = management;
    }

    public Double getSecurity() {
        return security;
    }

    public void setSecurity(Double security) {
        this.security = security;
    }

    public Double getNetReceipts() {
        return netReceipts;
    }

    public void setNetReceipts(Double netReceipts) {
        this.netReceipts = netReceipts;
    }

    @Override
    public String toString() {
        return "DifferenceReport{" +
                "securitiesName='" + securitiesName + '\'' +
                ", outMoney=" + outMoney +
                ", inMoney=" + inMoney +
                ", totalSum=" + totalSum +
                ", commission=" + commission +
                ", transfer=" + transfer +
                ", brokerage=" + brokerage +
                ", stamp=" + stamp +
                ", management=" + management +
                ", security=" + security +
                ", netReceipts=" + netReceipts +
                '}';
    }
}

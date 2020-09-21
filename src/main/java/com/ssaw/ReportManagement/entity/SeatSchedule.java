package com.ssaw.ReportManagement.entity;

public class SeatSchedule {
    private Double t_netreceipts;//实收金额
    private Double t_totalSum;//交易总金额
    private Double t_num;//数量
    private Double t_commission;//佣金费用
    private Double t_ransfer;//过户费
    private Double t_brokerage;//经手费
    private Double t_stamp;//印花税
    private Double t_management;//征管税
    private String fundid;//基金代码
    private String seateId;//席位Id
    private String  securitiesId;//证券代码
    private String fundName;//基金名称
    private String settlementDate;//结算日期

    public SeatSchedule() {
    }

    public SeatSchedule(Double t_netreceipts, Double t_totalSum, Double t_num, Double t_commission, Double t_ransfer, Double t_brokerage, Double t_stamp, Double t_management, String fundid, String seateId, String securitiesId, String fundName, String settlementDate) {
        this.t_netreceipts = t_netreceipts;
        this.t_totalSum = t_totalSum;
        this.t_num = t_num;
        this.t_commission = t_commission;
        this.t_ransfer = t_ransfer;
        this.t_brokerage = t_brokerage;
        this.t_stamp = t_stamp;
        this.t_management = t_management;
        this.fundid = fundid;
        this.seateId = seateId;
        this.securitiesId = securitiesId;
        this.fundName = fundName;
        this.settlementDate = settlementDate;
    }

    public Double getT_netreceipts() {
        return t_netreceipts;
    }

    public void setT_netreceipts(Double t_netreceipts) {
        this.t_netreceipts = t_netreceipts;
    }

    public Double getT_totalSum() {
        return t_totalSum;
    }

    public void setT_totalSum(Double t_totalSum) {
        this.t_totalSum = t_totalSum;
    }

    public Double getT_num() {
        return t_num;
    }

    public void setT_num(Double t_num) {
        this.t_num = t_num;
    }

    public Double getT_commission() {
        return t_commission;
    }

    public void setT_commission(Double t_commission) {
        this.t_commission = t_commission;
    }

    public Double getT_ransfer() {
        return t_ransfer;
    }

    public void setT_ransfer(Double t_ransfer) {
        this.t_ransfer = t_ransfer;
    }

    public Double getT_brokerage() {
        return t_brokerage;
    }

    public void setT_brokerage(Double t_brokerage) {
        this.t_brokerage = t_brokerage;
    }

    public Double getT_stamp() {
        return t_stamp;
    }

    public void setT_stamp(Double t_stamp) {
        this.t_stamp = t_stamp;
    }

    public Double getT_management() {
        return t_management;
    }

    public void setT_management(Double t_management) {
        this.t_management = t_management;
    }

    public String getFundid() {
        return fundid;
    }

    public void setFundid(String fundid) {
        this.fundid = fundid;
    }

    public String getSeateId() {
        return seateId;
    }

    public void setSeateId(String seateId) {
        this.seateId = seateId;
    }

    public String getSecuritiesId() {
        return securitiesId;
    }

    public void setSecuritiesId(String securitiesId) {
        this.securitiesId = securitiesId;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    @Override
    public String toString() {
        return "SeatSchedule{" +
                "t_netreceipts=" + t_netreceipts +
                ", t_totalSum=" + t_totalSum +
                ", t_num=" + t_num +
                ", t_commission=" + t_commission +
                ", t_ransfer=" + t_ransfer +
                ", t_brokerage=" + t_brokerage +
                ", t_stamp=" + t_stamp +
                ", t_management=" + t_management +
                ", fundid='" + fundid + '\'' +
                ", seateId='" + seateId + '\'' +
                ", securitiesId='" + securitiesId + '\'' +
                ", fundName='" + fundName + '\'' +
                ", settlementDate='" + settlementDate + '\'' +
                '}';
    }
}

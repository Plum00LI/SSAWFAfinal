package com.ssaw.BusinessDescription.entity;

/**
 * 交易所品种费率
 * @type:VarietiesRate
 * @version v1.0
 * @author:阙魁
 * @create:2020-09-01
 */
public class VarietiesRate {
    private int exchangeName; //交易所名称
    private int rateType;//费率类型
    private double stampDuty;//印花税
    private double transferFee;//过户费
    private double collectionRate;//征管费
    private double brokerage;//经手费

    public VarietiesRate() {
    }

    public VarietiesRate(int exchangeName, int rateType, double stampDuty, double transferFee, double collectionRate, double brokerage) {
        this.exchangeName = exchangeName;
        this.rateType = rateType;
        this.stampDuty = stampDuty;
        this.transferFee = transferFee;
        this.collectionRate = collectionRate;
        this.brokerage = brokerage;
    }

    @Override
    public String toString() {
        return "VarietiesRate{" +
                "exchangeName=" + exchangeName +
                ", rateType=" + rateType +
                ", stampDuty=" + stampDuty +
                ", transferFee=" + transferFee +
                ", collectionRate=" + collectionRate +
                ", brokerage=" + brokerage +
                '}';
    }

    public int getExchangeName() {
        return exchangeName;
    }

    public void setExchangeName(int exchangeName) {
        this.exchangeName = exchangeName;
    }

    public int getRateType() {
        return rateType;
    }

    public void setRateType(int rateType) {
        this.rateType = rateType;
    }

    public double getStampDuty() {
        return stampDuty;
    }

    public void setStampDuty(double stampDuty) {
        this.stampDuty = stampDuty;
    }

    public double getTransferFee() {
        return transferFee;
    }

    public void setTransferFee(double transferFee) {
        this.transferFee = transferFee;
    }

    public double getCollectionRate() {
        return collectionRate;
    }

    public void setCollectionRate(double collectionRate) {
        this.collectionRate = collectionRate;
    }

    public double getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(double brokerage) {
        this.brokerage = brokerage;
    }
}

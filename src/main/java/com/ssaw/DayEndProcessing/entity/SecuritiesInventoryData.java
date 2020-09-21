package com.ssaw.DayEndProcessing.entity;

/**
 * ClassName:    SecuritiesInventoryData
 * Package:    com.ssaw.DayEndProcessing.entity
 * Description:
 * Version:
 * Datetime:    2020/9/9   19:31
 * Author:   SYT
 */
public class SecuritiesInventoryData {
    private String securitiesId;// 证券Id
    private int todayNum;// 总数量
    private double todayTotal;// 总金额
    private double unitPrice;// 单位成本

    public SecuritiesInventoryData(){

    }

    public SecuritiesInventoryData(String securitiesId, int todayNum, double todayTotal, double unitPrice) {
        this.securitiesId = securitiesId;
        this.todayNum = todayNum;
        this.todayTotal = todayTotal;
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "SecuritiesInventoryData{" +
                "securitiesId='" + securitiesId + '\'' +
                ", todayTotal=" + todayTotal +
                ", todayNum=" + todayNum +
                ", unitPrice=" + unitPrice +
                '}';
    }

    public String getSecuritiesId() {
        return securitiesId;
    }

    public void setSecuritiesId(String securitiesId) {
        this.securitiesId = securitiesId;
    }

    public int getTodayNum() {
        return todayNum;
    }

    public void setTodayNum(int todayNum) {
        this.todayNum = todayNum;
    }

    public double getTodayTotal() {
        return todayTotal;
    }

    public void setTodayTotal(double todayTotal) {
        this.todayTotal = todayTotal;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
}

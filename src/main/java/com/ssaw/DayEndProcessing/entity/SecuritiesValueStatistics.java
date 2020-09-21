package com.ssaw.DayEndProcessing.entity;
/**
 * create by: 佘高鹏
 * description: TODO
 * 证券净值统计查询实体类
 * create time: 2020/9/11 16:16
 * version number 1.0
  * @Param: null
 * @return
 */
public class SecuritiesValueStatistics {
    private String securitiesId;
    private String securitiesName;
    private String securitiesType;
    private double closingPrice;
    private double securitiesNum;
    private double total;
    private double marketValue;
    private double totalPrice;

    public SecuritiesValueStatistics() {
    }

    public SecuritiesValueStatistics(String securitiesId, double totalPrice) {
        this.securitiesId = securitiesId;
        this.totalPrice = totalPrice;
    }

    public SecuritiesValueStatistics(String securitiesId, String securitiesName, String securitiesType, double closingPrice, double securitiesNum, double total, double marketValue, double totalPrice) {
        this.securitiesId = securitiesId;
        this.securitiesName = securitiesName;
        this.securitiesType = securitiesType;
        this.closingPrice = closingPrice;
        this.securitiesNum = securitiesNum;
        this.total = total;
        this.marketValue = marketValue;
        this.totalPrice = totalPrice;
    }

    public String getSecuritiesId() {
        return securitiesId;
    }

    public void setSecuritiesId(String securitiesId) {
        this.securitiesId = securitiesId;
    }

    public String getSecuritiesName() {
        return securitiesName;
    }

    public void setSecuritiesName(String securitiesName) {
        this.securitiesName = securitiesName;
    }

    public String getSecuritiesType() {
        return securitiesType;
    }

    public void setSecuritiesType(String securitiesType) {
        this.securitiesType = securitiesType;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }

    public double getSecuritiesNum() {
        return securitiesNum;
    }

    public void setSecuritiesNum(double securitiesNum) {
        this.securitiesNum = securitiesNum;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "SecuritiesValueStatistics{" +
                "securitiesId='" + securitiesId + '\'' +
                ", securitiesName='" + securitiesName + '\'' +
                ", securitiesType='" + securitiesType + '\'' +
                ", closingPrice=" + closingPrice +
                ", securitiesNum=" + securitiesNum +
                ", total=" + total +
                ", marketValue=" + marketValue +
                ", totalPrice=" + totalPrice +
                '}';
    }
}



package com.ssaw.DayEndProcessing.entity;

/**
*@program: TescComment
*@Description:证券库存与行情数据关联查询实体类
*@author: 瞿平
*@version:1.0
*@create: 2020-09-22
*/
public class StockSecuritiesJoinMarket {
    private String fundId;
    private String securitiesId;
    private Double tootaIPrice;
    private int securityPeriodFlag;

    public StockSecuritiesJoinMarket() {
    }

    public StockSecuritiesJoinMarket(String fundId, String securitiesId, Double tootaIPrice, int securityPeriodFlag) {
        this.fundId = fundId;
        this.securitiesId = securitiesId;
        this.tootaIPrice = tootaIPrice;
        this.securityPeriodFlag = securityPeriodFlag;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getSecuritiesId() {
        return securitiesId;
    }

    public void setSecuritiesId(String securitiesId) {
        this.securitiesId = securitiesId;
    }

    public Double getTootaIPrice() {
        return tootaIPrice;
    }

    public void setTootaIPrice(Double tootaIPrice) {
        this.tootaIPrice = tootaIPrice;
    }

    public int getSecurityPeriodFlag() {
        return securityPeriodFlag;
    }

    public void setSecurityPeriodFlag(int securityPeriodFlag) {
        this.securityPeriodFlag = securityPeriodFlag;
    }

    @Override
    public String toString() {
        return "StockSecuritiesJoinMarket{" +
                "fundId='" + fundId + '\'' +
                ", securitiesId='" + securitiesId + '\'' +
                ", tootaIPrice='" + tootaIPrice + '\'' +
                ", securityPeriodFlag='" + securityPeriodFlag + '\'' +
                '}';
    }
}

package com.ssaw.DayEndProcessing.entity;

/**
 *@program: FA
 *@description: 资产估值实体类
 *@author: 瞿平
 *@create: 2020-09-08 11:25
 **/
public class AssetValuation {
    private String toDay;//估值日期
    private String securitiesId;//证券编号
    private Integer securitiesType;//证券类型
    private Double closingPrice;//股市收盘价格
    private Integer securitiesNum;//证券购买的数量
    private Double price;//单位成本
    private Double total;//购买的总价格
    private String fundId;//基金编号
    private Double assetValuationVal;//资产估值
    private String dateTime;//交易日期
    private String settlementDate;//结算日期
    private Double totalSum;//交易总金额
    private Integer flag;//1-流入  2-流出
    private Double actualMoney;//TA实际交易金额
    private Integer transactionType;//TA交易类型
    private String strAppraisement;

    public AssetValuation() {
    }

    public AssetValuation(String toDay, String securitiesId, Integer securitiesType, Double closingPrice, Integer securitiesNum, Double price, Double total, String fundId, Double assetValuationVal, String dateTime, String settlementDate, Double totalSum, Integer flag, Double actualMoney, Integer transactionType, String strAppraisement) {
        this.toDay = toDay;
        this.securitiesId = securitiesId;
        this.securitiesType = securitiesType;
        this.closingPrice = closingPrice;
        this.securitiesNum = securitiesNum;
        this.price = price;
        this.total = total;
        this.fundId = fundId;
        this.assetValuationVal = assetValuationVal;
        this.dateTime = dateTime;
        this.settlementDate = settlementDate;
        this.totalSum = totalSum;
        this.flag = flag;
        this.actualMoney = actualMoney;
        this.transactionType = transactionType;
        this.strAppraisement = strAppraisement;
    }

    public String getToDay() {
        return toDay;
    }

    public void setToDay(String toDay) {
        this.toDay = toDay;
    }

    public String getSecuritiesId() {
        return securitiesId;
    }

    public void setSecuritiesId(String securitiesId) {
        this.securitiesId = securitiesId;
    }

    public Integer getSecuritiesType() {
        return securitiesType;
    }

    public void setSecuritiesType(Integer securitiesType) {
        this.securitiesType = securitiesType;
    }

    public Double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(Double closingPrice) {
        this.closingPrice = closingPrice;
    }

    public Integer getSecuritiesNum() {
        return securitiesNum;
    }

    public void setSecuritiesNum(Integer securitiesNum) {
        this.securitiesNum = securitiesNum;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public Double getAssetValuationVal() {
        return assetValuationVal;
    }

    public void setAssetValuationVal(Double assetValuationVal) {
        this.assetValuationVal = assetValuationVal;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Double getActualMoney() {
        return actualMoney;
    }

    public void setActualMoney(Double actualMoney) {
        this.actualMoney = actualMoney;
    }

    public Integer getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Integer transactionType) {
        this.transactionType = transactionType;
    }

    public String getStrAppraisement() {
        return strAppraisement;
    }

    public void setStrAppraisement(String strAppraisement) {
        this.strAppraisement = strAppraisement;
    }


    @Override
    public String toString() {
        return "AssetValuation{" +
                "toDay='" + toDay + '\'' +
                ", securitiesId='" + securitiesId + '\'' +
                ", securitiesType=" + securitiesType +
                ", closingPrice=" + closingPrice +
                ", securitiesNum=" + securitiesNum +
                ", price=" + price +
                ", total=" + total +
                ", fundId='" + fundId + '\'' +
                ", appraisementVal=" + assetValuationVal +
                ", dateTime='" + dateTime + '\'' +
                ", settlementDate='" + settlementDate + '\'' +
                ", totalSum=" + totalSum +
                ", flag=" + flag +
                ", actualMoney=" + actualMoney +
                ", transactionType=" + transactionType +
                ", strAppraisement='" + strAppraisement + '\'' +
                '}';
    }
}

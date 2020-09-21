package com.ssaw.ReportManagement.entity;

/**
 * ClassName:    SecuritiesMarket
 * Package:    com.ssaw.ReportManagement.entity
 * Description: 证券市场变动表实体类
 * Version:
 * Datetime:    2020/9/16   14:51
 * Author:   SYT
 */
public class SecuritiesMarket {
    private String securitiesId;// 证券代码
    private String securitiesName;// 证券名称
    private int securitiesNum;// 库存数量
    private double price;// 单位成本
    private double closingPrice;// 行情价格
    private double mvcr;// 市值变动比
    private double mvnv;// 市值占净值
    private String fundId;//基金ID
    private double projectName;//资产净值
    private String dateTime;//除权日期
    private double projectId;



    public SecuritiesMarket(){

    }

    public SecuritiesMarket(String securitiesId, String securitiesName, int securitiesNum, double price, double closingPrice, double mvcr, double mvnv) {
        this.securitiesId = securitiesId;
        this.securitiesName = securitiesName;
        this.securitiesNum = securitiesNum;
        this.price = price;
        this.closingPrice = closingPrice;
        this.mvcr = mvcr;
        this.mvnv = mvnv;
    }

    public SecuritiesMarket(String securitiesId, String securitiesName, int securitiesNum, double price, double closingPrice, double mvcr, double mvnv, String fundId, double projectName, String dateTime, double projectId) {
        this.securitiesId = securitiesId;
        this.securitiesName = securitiesName;
        this.securitiesNum = securitiesNum;
        this.price = price;
        this.closingPrice = closingPrice;
        this.mvcr = mvcr;
        this.mvnv = mvnv;
        this.fundId = fundId;
        this.projectName = projectName;
        this.dateTime = dateTime;
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "SecuritiesMarket{" +
                "securitiesId='" + securitiesId + '\'' +
                ", securitiesName='" + securitiesName + '\'' +
                ", securitiesNum=" + securitiesNum +
                ", price=" + price +
                ", closingPrice=" + closingPrice +
                ", mvcr=" + mvcr +
                ", mvnv=" + mvnv +
                ", fundId='" + fundId + '\'' +
                ", projectName=" + projectName +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }

    public double getProjectId() {
        return projectId;
    }

    public void setProjectId(double projectId) {
        this.projectId = projectId;
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

    public int getSecuritiesNum() {
        return securitiesNum;
    }

    public void setSecuritiesNum(int securitiesNum) {
        this.securitiesNum = securitiesNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }

    public double getMvcr() {
        return mvcr;
    }

    public void setMvcr(double mvcr) {
        this.mvcr = mvcr;
    }

    public double getMvnv() {
        return mvnv;
    }

    public void setMvnv(double mvnv) {
        this.mvnv = mvnv;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public double getProjectName() {
        return projectName;
    }

    public void setProjectName(double projectName) {
        this.projectName = projectName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}

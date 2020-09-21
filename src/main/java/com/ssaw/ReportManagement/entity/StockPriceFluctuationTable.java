package com.ssaw.ReportManagement.entity;

/**
 * ClassName:    StockPriceFluctuationTable
 * Package:    com.ssaw.ReportManagement.entity
 * Description: 股票价格波动表
 * Version:     1.0
 * Datetime:    2020/9/18 14:51
 * Author:   阙魁
 */
public class StockPriceFluctuationTable {
    private String securitiesId;//证券Id
    private String securitiesName;//证券名称
    private String dateTime;//时间
    private double todayIce;//当日收市价
    private double beforeIce;//前日收市价
    private double quoteChange;//涨跌幅(%)

    public StockPriceFluctuationTable() {
    }

    public StockPriceFluctuationTable(String securitiesId, String securitiesName, String dateTime, double todayIce, double beforeIce, double quoteChange) {
        this.securitiesId = securitiesId;
        this.securitiesName = securitiesName;
        this.dateTime = dateTime;
        this.todayIce = todayIce;
        this.beforeIce = beforeIce;
        this.quoteChange = quoteChange;
    }

    @Override
    public String toString() {
        return "StockPriceFluctuationTable{" +
                "securitiesId='" + securitiesId + '\'' +
                ", securitiesName='" + securitiesName + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", todayIce=" + todayIce +
                ", beforeIce=" + beforeIce +
                ", quoteChange=" + quoteChange +
                '}';
    }

    public double getBeforeIce() {
        return beforeIce;
    }

    public void setBeforeIce(double beforeIce) {
        this.beforeIce = beforeIce;
    }

    public String getSecuritiesId() {
        return securitiesId;
    }

    public void setSecuritiesId(String securitiesId) {
        this.securitiesId = securitiesId;
    }



    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getSecuritiesName() {
        return securitiesName;
    }

    public void setSecuritiesName(String securitiesName) {
        this.securitiesName = securitiesName;
    }

    public double getTodayIce() {
        return todayIce;
    }

    public void setTodayIce(double todayIce) {
        this.todayIce = todayIce;
    }



    public double getQuoteChange() {
        return quoteChange;
    }

    public void setQuoteChange(double quoteChange) {
        this.quoteChange = quoteChange;
    }
}

package com.ssaw.BusinessDescription.entity;
/**
 * 债券
 * @type Securities的实体类
 * @author fusaiying
 * @date 2020-09-01
 * @version 1.0
 */

public class Securities {
    private String securitiesId;//证券编号
    private String securitiesName;//证券名称
    private int securitiesType;//证券类型 1=债券  2=股票
    private String issueDate;//发行日期
    private String delayDate;//延迟日期 T+1 ……
    private String stockId;//股票板块
    private int exchange;//交易所  1=上交所 2=深交所
    private String securitiesDesc;//备注

    public Securities() {
    }

    public Securities(String securitiesId, String securitiesName, int securitiesType, String issueDate, String delayDate, String stockId, int exchange, String securitiesDesc) {
        this.securitiesId = securitiesId;
        this.securitiesName = securitiesName;
        this.securitiesType = securitiesType;
        this.issueDate = issueDate;
        this.delayDate = delayDate;
        this.stockId = stockId;
        this.exchange = exchange;
        this.securitiesDesc = securitiesDesc;
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

    public int getSecuritiesType() {
        return securitiesType;
    }

    public void setSecuritiesType(int securitiesType) {
        this.securitiesType = securitiesType;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getDelayDate() {
        return delayDate;
    }

    public void setDelayDate(String delayDate) {
        this.delayDate = delayDate;
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }

    public int getExchange() {
        return exchange;
    }

    public void setExchange(int exchange) {
        this.exchange = exchange;
    }

    public String getSecuritiesDesc() {
        return securitiesDesc;
    }

    public void setSecuritiesDesc(String securitiesDesc) {
        this.securitiesDesc = securitiesDesc;
    }

    @Override
    public String toString() {
        return "Securities{" +
                "securitiesId='" + securitiesId + '\'' +
                ", securitiesName='" + securitiesName + '\'' +
                ", securitiesType=" + securitiesType +
                ", issueDate='" + issueDate + '\'' +
                ", delayDate='" + delayDate + '\'' +
                ", stockId='" + stockId + '\'' +
                ", exchange=" + exchange +
                ", securitiesDesc='" + securitiesDesc + '\'' +
                '}';
    }
}

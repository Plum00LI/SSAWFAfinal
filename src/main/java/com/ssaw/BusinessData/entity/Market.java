package com.ssaw.BusinessData.entity;

/**
*@program: TescComment
*@Description:行情数据实体类
*@author: 瞿平
*@version:1.0
*@create: 2020-09-01
*/
public class Market {
    /*行情ID marketId Varchar(50) 该表的ID
    证券ID securitiesId Varchar(50)
    证券信息表ID，引用证券编号 securitiesId，证券名称 securitiesName
    日期 dateTime Date 录入证券的日期
    开盘价格 openPrice Number（14,2）当天单份证券的开盘价格
    闭市价格 closingPrice Number(14,2) 交易所闭市之后的价格
    备注 desc Varchar2(50) 行情数据的其他信息*/

    /**
     * 行情Id
     */
    private String marketId;
    /**
     *证券编号
     */
    private String securitiesId;
    /**
     * 证券名称
     */
    private String securitiesName;
    /**
     * 日期
     */
    private String dateTime;
    /**
     * 当天单份证券的开盘价格
     */
    private double openPrice;
    /**
     * 交易所闭市之后的价格
     */
    private double closingPrice;
    /**
     * 备注
     */
    private String marketDesc;


    public Market() {
    }

    public Market(String marketId, String securitiesId, String securitiesName, String dateTime, double openPrice, double closingPrice, String marketDesc) {
        this.marketId = marketId;
        this.securitiesId = securitiesId;
        this.securitiesName = securitiesName;
        this.dateTime = dateTime;
        this.openPrice = openPrice;
        this.closingPrice = closingPrice;
        this.marketDesc = marketDesc;
    }

    public String getMarketId() {
        return marketId;
    }

    public void setMarketId(String marketId) {
        this.marketId = marketId;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getOpenPrice() {
        return openPrice;
    }

    public void setOpenPrice(double openPrice) {
        this.openPrice = openPrice;
    }

    public double getClosingPrice() {
        return closingPrice;
    }

    public void setClosingPrice(double closingPrice) {
        this.closingPrice = closingPrice;
    }

    public String getMarketDesc() {
        return marketDesc;
    }

    public void setMarketDesc(String marketDesc) {
        this.marketDesc = marketDesc;
    }


    @Override
    public String toString() {
        return "Market{" +
                "行情ID=" + marketId +
                ", 证券编号='" + securitiesId + '\'' +
                ", 证券名称=" + securitiesName +
                ", 日期='" + dateTime + '\'' +
                ", 开盘价格=" + openPrice +
                ", 闭市价格=" + closingPrice +
                ", 备注='" + marketDesc + '\'' +
                '}';
    }
}

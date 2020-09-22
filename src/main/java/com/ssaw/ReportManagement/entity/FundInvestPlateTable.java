package com.ssaw.ReportManagement.entity;
/**
 * create by: 佘高鹏
 * description: TODO
 * 基金投资板块报表bin
 * create time: 2020/9/19 9:43
 * version number 1.0
  * @Param: null
 * @return
 */
public class FundInvestPlateTable {

    private String stockId;
    private String stockName;
    private double securitiesNum;
    private double marketValue;
    private double marketValueStatistics;

    public FundInvestPlateTable(String stockId,  String stockName, double securitiesNum, double marketValue, double marketValueStatistics) {
        this.stockId = stockId;

        this.stockName = stockName;
        this.securitiesNum = securitiesNum;
        this.marketValue = marketValue;
        this.marketValueStatistics = marketValueStatistics;
    }

    public FundInvestPlateTable(String stockName, double securitiesNum, double marketValue, double marketValueStatistics) {
        this.stockName = stockName;
        this.securitiesNum = securitiesNum;
        this.marketValue = marketValue;
        this.marketValueStatistics = marketValueStatistics;
    }

    public FundInvestPlateTable() {
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }



    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getSecuritiesNum() {
        return securitiesNum;
    }

    public void setSecuritiesNum(double securitiesNum) {
        this.securitiesNum = securitiesNum;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
    }

    public double getMarketValueStatistics() {
        return marketValueStatistics;
    }

    public void setMarketValueStatistics(double marketValueStatistics) {
        this.marketValueStatistics = marketValueStatistics;
    }

    @Override
    public String toString() {
        return "FundInvestPlateTable{" +
                "stockId='" + stockId + '\'' +

                ", stockName='" + stockName + '\'' +
                ", securitiesNum=" + securitiesNum +
                ", marketValue=" + marketValue +
                ", marketValueStatistics=" + marketValueStatistics +
                '}';
    }
}

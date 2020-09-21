package com.ssaw.ReportManagement.entity;

/**
 * ClassName:    StockEquityInformationSheet
 * Package:    com.ssaw.ReportManagement.entity
 * Description: 股票权益数据表
 * Version:     1.0
 * Datetime:    2020/9/18 14:51
 * Author:   阙魁
 */
public class StockEquityInformationSheet {
    private String securityId;//证券Id
    private String securitiesName;//证券名称
    private String equitiesExright;//权益除权日
    private double securitiesNum;//数量
    private double total;//总金额
    private int equitiesType;//权益类型  1.送股   2.分红
    private int proportion;//比例

    public StockEquityInformationSheet() {
    }

    public StockEquityInformationSheet(String securityId, String securitiesName, String equitiesExright, double securitiesNum, double total, int equitiesType, int proportion) {
        this.securityId = securityId;
        this.securitiesName = securitiesName;
        this.equitiesExright = equitiesExright;
        this.securitiesNum = securitiesNum;
        this.total = total;
        this.equitiesType = equitiesType;
        this.proportion = proportion;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public String getSecuritiesName() {
        return securitiesName;
    }

    public void setSecuritiesName(String securitiesName) {
        this.securitiesName = securitiesName;
    }

    public String getEquitiesExright() {
        return equitiesExright;
    }

    public void setEquitiesExright(String equitiesExright) {
        this.equitiesExright = equitiesExright;
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

    public int getEquitiesType() {
        return equitiesType;
    }

    public void setEquitiesType(int equitiesType) {
        this.equitiesType = equitiesType;
    }

    public int getProportion() {
        return proportion;
    }

    public void setProportion(int proportion) {
        this.proportion = proportion;
    }

    @Override
    public String toString() {
        return "StockEquityInformationSheet{" +
                "securityId='" + securityId + '\'' +
                ", securitiesName='" + securitiesName + '\'' +
                ", equitiesExright='" + equitiesExright + '\'' +
                ", securitiesNum=" + securitiesNum +
                ", total=" + total +
                ", equitiesType=" + equitiesType +
                ", proportion=" + proportion +
                '}';
    }
}

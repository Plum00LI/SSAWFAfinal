package com.ssaw.InventoryManagement.entity;

/**
*@program: TescComment
*@Description:证券库存实体类
*@author: 瞿平
*@version:1.0
*@create: 2020-09-01
*/
public class SecuritiesInventory {
    /*证券库存ID securitiesInventoryId Varchar2(50) PK 证券库存ID
    证券库存日期 dateTime Date 证券库存日期
    证券信息表ID securitiesId Varchar2(50)
    证券信息表ID，引用证券编号securitiesId，证券名securitiesName
    基金表ID fundId Varchar2(50) 基金表Id 印入基金代码
    是否导入其他系统数据 securityPeriodFlag Number(14,2)
    是否从其他系统导入的期初数据  0：不是  1：是
    证券的数量 securitiesNum Number(14,2) 计算总金额条件
    单位成本 price Number(14,2) 计算总金额条件
    总金额 total Number(14,2) 证券数量*单位成本
    备注 desc Number(1) 其他信息*/

    /**
     * 证券库存ID
     */
    private String securitiesInventoryId;
    /**
     * 证券库存日期
     */
    private String dateTime;
    /**
     * 证券编号
     */
    private String securitiesId;
    /**
     * 证券名
     */
    private String securitiesName;
    /**
     * 基金代码
     */
    private String fundId;
    /**
     * 是否从其他系统导入的期初数据  0：不是  1：是
     */
    private int securityPeriodFlag;
    /**
     * 证券的数量
     */
    private int securitiesNum;
    /**
     * 单位成本 price
     */
    private double price;
    /**
     * 总金额 total
     */
    private double total;
    /**
     * 备注 desc
     */
    private String securitiesInventoryDesc;

    public SecuritiesInventory() {
    }

    public SecuritiesInventory(String securitiesInventoryId, String dateTime, String securitiesId, String securitiesName, String fundId, int securityPeriodFlag, int securitiesNum, double price, double total, String securitiesInventoryDesc) {
        this.securitiesInventoryId = securitiesInventoryId;
        this.dateTime = dateTime;
        this.securitiesId = securitiesId;
        this.securitiesName = securitiesName;
        this.fundId = fundId;
        this.securityPeriodFlag = securityPeriodFlag;
        this.securitiesNum = securitiesNum;
        this.price = price;
        this.total = total;
        this.securitiesInventoryDesc = securitiesInventoryDesc;
    }

    public String getSecuritiesInventoryId() {
        return securitiesInventoryId;
    }

    public void setSecuritiesInventoryId(String securitiesInventoryId) {
        this.securitiesInventoryId = securitiesInventoryId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public int getSecurityPeriodFlag() {
        return securityPeriodFlag;
    }

    public void setSecurityPeriodFlag(int securityPeriodFlag) {
        this.securityPeriodFlag = securityPeriodFlag;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getSecuritiesInventoryDesc() {
        return securitiesInventoryDesc;
    }

    public void setSecuritiesInventoryDesc(String securitiesInventoryDesc) {
        this.securitiesInventoryDesc = securitiesInventoryDesc;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "证券库存ID=" + securitiesInventoryId +
                ", 证券库存日期='" + dateTime + '\'' +
                ", 证券编号=" + securitiesId +
                ", 证券名='" + securitiesName + '\'' +
                ", 基金代码='" + fundId + '\'' +
                ", 是否从其他系统导入的期初数据  0：不是  1：是=" + securityPeriodFlag +
                ", 证券的数量=" + securitiesNum +
                ", 单位成本=" + price +
                ", 总金额=" + total +
                ", 备注='" + securitiesInventoryDesc + '\'' +
                '}';
    }
}

package com.ssaw.InventoryManagement.entity;

/**
 * ClassName:    TaInventory
 * Package:    com.ssaw.InventoryManagement.entity
 * Description:
 * Version:   1.0
 * Datetime:    2020/9/1   15:38
 * Author:   SYT
 */
public class TaInventory {
    //TA库存Id
    private String taInventoryId;
    //FK 基金Id来自基金表
    private String fundId;
    //Ta数量
    private int taNum;
    //现金余额
    private double taTotal;
    //统计日期
    private String dateTime;
    //是否从其他系统导入的期初数据  0：不是  1：是
    private int securityPeriodFlag;
     //备注
    private String taInventoryDesc;

    private String accountName;

    public TaInventory(){

    }

    public TaInventory(String taInventoryId, String fundId, int taNum, double taTotal, String dateTime, int securityPeriodFlag, String taInventoryDesc, String accountName) {
        this.taInventoryId = taInventoryId;
        this.fundId = fundId;
        this.taNum = taNum;
        this.taTotal = taTotal;
        this.dateTime = dateTime;
        this.securityPeriodFlag = securityPeriodFlag;
        this.taInventoryDesc = taInventoryDesc;
        this.accountName = accountName;
    }

    public TaInventory(String taInventoryId, String fundId, int taNum, double taTotal, String dateTime, int securityPeriodFlag, String taInventoryDesc) {
        this.taInventoryId = taInventoryId;
        this.fundId = fundId;
        this.taNum = taNum;
        this.taTotal = taTotal;
        this.dateTime = dateTime;
        this.securityPeriodFlag = securityPeriodFlag;
        this.taInventoryDesc = taInventoryDesc;
    }

    @Override
    public String toString() {
        return "TaInventory{" +
                "taInventoryId='" + taInventoryId + '\'' +
                ", fundId='" + fundId + '\'' +
                ", taNum=" + taNum +
                ", taTotal=" + taTotal +
                ", dateTime='" + dateTime + '\'' +
                ", securityPeriodFlag=" + securityPeriodFlag +
                ", taInventoryDesc='" + taInventoryDesc + '\'' +
                '}';
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getTaInventoryId() {
        return taInventoryId;
    }

    public void setTaInventoryId(String taInventoryId) {
        this.taInventoryId = taInventoryId;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public int getTaNum() {
        return taNum;
    }

    public void setTaNum(int taNum) {
        this.taNum = taNum;
    }

    public double getTaTotal() {
        return taTotal;
    }

    public void setTaTotal(double taTotal) {
        this.taTotal = taTotal;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getSecurityPeriodFlag() {
        return securityPeriodFlag;
    }

    public void setSecurityPeriodFlag(int securityPeriodFlag) {
        this.securityPeriodFlag = securityPeriodFlag;
    }

    public String getTaInventoryDesc() {
        return taInventoryDesc;
    }

    public void setTaInventoryDesc(String taInventoryDesc) {
        this.taInventoryDesc = taInventoryDesc;
    }
}

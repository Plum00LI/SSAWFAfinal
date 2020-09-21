package com.ssaw.InventoryManagement.entity;

/**
 * ClassName:证券应收应付库存
 * Datetime:2020-09-14
 * Author:sunH
 */

public class SecuritiesClosedPayInventory {
    private String securitiesClosedPayInventoryId; //证券应收应付存库Id
    private String dateTime;//业务日期
    private String fundId;//基金信息表Id
    private String securitiesId;//证券信息表ID  securities表
    private String securitiesName;
    private int securitiesType;//证券应收应付类型 1=估值增值 2=证券清算款 3=债券利息
    private int flag;//业务状态 1流入，-1流出
    private Double totalPrice;//总金额*
    private String securitiesClosedPayDesc;//备注
    private int securityPeriodFlag;//期初标志 是否从其他系统导入得期初数据 0：不是 1：是
    public SecuritiesClosedPayInventory(){

    }

    public SecuritiesClosedPayInventory(String securitiesClosedPayInventoryId, String dateTime, String fundId, String securitiesId, String securitiesName,
                                        int securitiesType, int flag, Double totalPrice, String securitiesClosedPayDesc, int securityPeriodFlag) {
        this.securitiesClosedPayInventoryId = securitiesClosedPayInventoryId;
        this.dateTime = dateTime;
        this.fundId = fundId;
        this.securitiesId = securitiesId;
        this.securitiesName = securitiesName;
        this.securitiesType = securitiesType;
        this.flag = flag;
        this.totalPrice = totalPrice;
        this.securitiesClosedPayDesc = securitiesClosedPayDesc;
        this.securityPeriodFlag = securityPeriodFlag;
    }

    /**
     *
     * @param securitiesClosedPayInventoryId    证券应收应付存库Id
     * @param dateTime  业务日期
     * @param fundId    基金信息表Id
     * @param securitiesId  证券信息表ID  securities表
     * @param securitiesType    证券应收应付类型 1=估值款 2=证券清算款 3=债券利息
     * @param flag  业务状态 1流入，-1流出
     * @param totalPrice    总金额*
     * @param securitiesClosedPayDesc   备注
     * @param securityPeriodFlag    期初标志 是否从其他系统导入得期初数据 0：不是 1：是
     */
    public SecuritiesClosedPayInventory(String securitiesClosedPayInventoryId, String dateTime, String fundId, String securitiesId, int securitiesType, int flag, Double totalPrice, String securitiesClosedPayDesc, int securityPeriodFlag) {
        this.securitiesClosedPayInventoryId = securitiesClosedPayInventoryId;
        this.dateTime = dateTime;
        this.fundId = fundId;
        this.securitiesId = securitiesId;
        this.securitiesType = securitiesType;
        this.flag = flag;
        this.totalPrice = totalPrice;
        this.securitiesClosedPayDesc = securitiesClosedPayDesc;
        this.securityPeriodFlag = securityPeriodFlag;
    }




    public String getSecuritiesClosedPayInventoryId() {
        return securitiesClosedPayInventoryId;
    }

    public void setSecuritiesClosedPayInventoryId(String securitiesClosedPayInventoryId) {
        this.securitiesClosedPayInventoryId = securitiesClosedPayInventoryId;
    }


    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSecuritiesClosedPayDesc() {
        return securitiesClosedPayDesc;
    }

    public void setSecuritiesClosedPayDesc(String securitiesClosedPayDesc) {
        this.securitiesClosedPayDesc = securitiesClosedPayDesc;
    }

    public int getSecurityPeriodFlag() {
        return securityPeriodFlag;
    }

    public void setSecurityPeriodFlag(int securityPeriodFlag) {
        this.securityPeriodFlag = securityPeriodFlag;
    }


    @Override
    public String toString() {
        return "证券应收应付存库{" +
                "证券应收应付存库Id='" + securitiesClosedPayInventoryId + '\'' +
                ", 业务日期='" + dateTime + '\'' +
                ", 基金ID='" + fundId + '\'' +
                ", 证券ID='" + securitiesId + '\'' +
                ", 证券应收应付类型=" + securitiesType +
                ", 业务状态=" + flag +
                ", 总金额=" + totalPrice +
                ", 备注='" + securitiesClosedPayDesc + '\'' +
                ", 期初标志=" + securityPeriodFlag +
                '}';
    }
}

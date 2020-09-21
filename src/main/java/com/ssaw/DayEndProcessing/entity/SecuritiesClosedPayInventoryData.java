package com.ssaw.DayEndProcessing.entity;

/**
 * ClassName:    SecuritiesClosedPayInventoryData
 * Package:    com.ssaw.DayEndProcessing.entity
 * Description: 证券应收应付库存数据
 * Version:
 * Datetime:    2020/9/11   23:13
 * Author:   SYT
 */
public class SecuritiesClosedPayInventoryData {
    private String securitiesId;
    private Double total;
    private int flag;

    public SecuritiesClosedPayInventoryData(){

    }

    public SecuritiesClosedPayInventoryData(String securitiesId, Double total, int flag) {
        this.securitiesId = securitiesId;
        this.total = total;
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "SecuritiesClosedPayInventoryData{" +
                "securitiesId='" + securitiesId + '\'' +
                ", total=" + total +
                ", flag=" + flag +
                '}';
    }

    public String getSecuritiesId() {
        return securitiesId;
    }

    public void setSecuritiesId(String securitiesId) {
        this.securitiesId = securitiesId;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}

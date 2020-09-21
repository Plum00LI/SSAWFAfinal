package com.ssaw.DayEndProcessing.entity;

/**
 * ClassName:    TaInventoryData
 * Package:    com.ssaw.DayEndProcessing.entity
 * Description: ta库存数据
 * Version:
 * Datetime:    2020/9/11   10:12
 * Author:   SYT
 */
public class TaInventoryData {
    private double taTotal;
    private int taNum;
    private String fundId;

    public TaInventoryData(){

    }

    public TaInventoryData(double taTotal, int taNum, String fundId) {
        this.taTotal = taTotal;
        this.taNum = taNum;
        this.fundId = fundId;
    }

    @Override
    public String toString() {
        return "TaInventoryData{" +
                "taTotal=" + taTotal +
                ", taNum=" + taNum +
                ", fundId='" + fundId + '\'' +
                '}';
    }

    public double getTaTotal() {
        return taTotal;
    }

    public void setTaTotal(double taTotal) {
        this.taTotal = taTotal;
    }

    public int getTaNum() {
        return taNum;
    }

    public void setTaNum(int taNum) {
        this.taNum = taNum;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }
}

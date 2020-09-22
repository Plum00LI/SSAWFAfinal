package com.ssaw.ReportManagement.entity;
/**
 * create by: 佘高鹏
 * description: TODO
 * 基金投资板块报表饼状图bin
 * create time: 2020/9/21 19:55
 * version number 1.0
  * @Param: null
 * @return
 */
public class FundInvestPlateTable2 {
    private  String name;
    private Double value;

    public FundInvestPlateTable2(String name, Double value) {
        this.name = name;
        this.value = value;
    }

    public FundInvestPlateTable2() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getValue() {
        return value;
    }

    public void setCost(Double value) {
        this.value = value;
    }
}

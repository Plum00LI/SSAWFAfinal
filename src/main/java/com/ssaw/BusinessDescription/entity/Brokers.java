package com.ssaw.BusinessDescription.entity;

/**
 * @program:TescComment 券商信息表brokers
 * @Description:实体类
 * @author:孙浩
 * @create:2020-09-07
 */
public class Brokers {
    private String brokersId;//券商编号
    private String brokersName;//券商名称
    private String brokersInstructions;//券商说明
    private String brokersDesc;//券商备注

    public Brokers() {
    }

    public Brokers(String brokersId, String brokersName, String brokersInstructions, String brokersDesc) {
        this.brokersId = brokersId;
        this.brokersName = brokersName;
        this.brokersInstructions = brokersInstructions;
        this.brokersDesc = brokersDesc;
    }

    public String getBrokersId() {
        return brokersId;
    }

    public void setBrokersId(String brokersId) {
        this.brokersId = brokersId;
    }

    public String getBrokersName() {
        return brokersName;
    }

    public void setBrokersName(String brokersName) {
        this.brokersName = brokersName;
    }

    public String getBrokersInstructions() {
        return brokersInstructions;
    }

    public void setBrokersInstructions(String brokersInstructions) {
        this.brokersInstructions = brokersInstructions;
    }

    public String getBrokersDesc() {
        return brokersDesc;
    }

    public void setBrokersDesc(String brokersDesc) {
        this.brokersDesc = brokersDesc;
    }

    @Override
    public String toString() {
        return "券商设置{" +
                "券商编号='" + brokersId + '\'' +
                ", 券商名称='" + brokersName + '\'' +
                ", 券商说明='" + brokersInstructions + '\'' +
                ", 券商备注='" + brokersDesc + '\'' +
                '}';
    }
}

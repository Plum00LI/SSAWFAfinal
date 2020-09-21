package com.ssaw.BusinessDescription.entity;

/**
 * 债券信息设置
 * @type:Bond
 * @version:v1.0
 * @authod:洪彬峰
 * @date:2020-09-01
 */
public class Bond {
    private String securitiesId;        //PK ,FK证券编号  来自证券信息表
    private String bondName;            //债券名称
    private String drawStartDate;       //计息起始日期
    private String drawEndDate;         //计息结束日期
    private int    bondType;            //债券类型  1=银行间  0=非银行间
    private double parRate;             //票面利率
    private double bondRate;            //债券利息
    private double bondRateAmount;      //票面金额
    private int    payInterestNum ;      //付息次数  1=1年一次  2=1年俩次 3=1年四次
    private String bondDesc;             //备注

    public Bond(String securitiesId, String bondName, String drawStartDate, String drawEndDate, int bondType, double parRate, double bondRate, double bondRateAmount, int payInterestNum, String bondDesc) {
        this.securitiesId = securitiesId;
        this.bondName = bondName;
        this.drawStartDate = drawStartDate;
        this.drawEndDate = drawEndDate;
        this.bondType = bondType;
        this.parRate = parRate;
        this.bondRate = bondRate;
        this.bondRateAmount = bondRateAmount;
        this.payInterestNum = payInterestNum;
        this.bondDesc = bondDesc;
    }

    public  Bond(){}

    public String getSecuritiesId() {
        return securitiesId;
    }

    public void setSecuritiesId(String securitiesId) {
        this.securitiesId = securitiesId;
    }

    public String getBondName() {
        return bondName;
    }

    public void setBondName(String bondName) {
        this.bondName = bondName;
    }

    public String getDrawStartDate() {
        return drawStartDate;
    }

    public void setDrawStartDate(String drawStartDate) {
        this.drawStartDate = drawStartDate;
    }

    public String getDrawEndDate() {
        return drawEndDate;
    }

    public void setDrawEndDate(String drawEndDate) {
        this.drawEndDate = drawEndDate;
    }

    public int getBondType() {
        return bondType;
    }

    public void setBondType(int bondType) {
        this.bondType = bondType;
    }

    public double getParRate() {
        return parRate;
    }

    public void setParRate(double parRate) {
        this.parRate = parRate;
    }

    public double getBondRate() {
        return bondRate;
    }

    public void setBondRate(double bondRate) {
        this.bondRate = bondRate;
    }

    public double getBondRateAmount() {
        return bondRateAmount;
    }

    public void setBondRateAmount(double bondRateAmount) {
        this.bondRateAmount = bondRateAmount;
    }

    public int getPayInterestNum() {
        return payInterestNum;
    }

    public void setPayInterestNum(int payInterestNum) {
        this.payInterestNum = payInterestNum;
    }

    public String getBondDesc() {
        return bondDesc;
    }

    public void setBondDesc(String bondDesc) {
        this.bondDesc = bondDesc;
    }

    @Override
    public String toString() {
        return "Bond{" +
                "securitiesId='" + securitiesId + '\'' +
                ", bondName='" + bondName + '\'' +
                ", drawStartDate='" + drawStartDate + '\'' +
                ", drawEndDate='" + drawEndDate + '\'' +
                ", bondType=" + bondType +
                ", parRate=" + parRate +
                ", bondRate=" + bondRate +
                ", bondRateAmount='" + bondRateAmount + '\'' +
                ", payInterestNum=" + payInterestNum +
                ", bondDesc='" + bondDesc + '\'' +
                '}';
    }
}

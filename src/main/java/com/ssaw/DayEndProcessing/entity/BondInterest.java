package com.ssaw.DayEndProcessing.entity;
/**
 * 收益计提-现金
 * @type 实体类
 * @author fusaiying
 * @date 2020-09-10
 * @version 1.0
 */
public class BondInterest {

    private String securitiesId;  //证券编号
    private String bondName;  //证券名称
    private String drawStartDate;//计息起始日
    private  String drawEndDate; //计息结束日
    private int bondType; //债券类型
    private double parRate; //票面利率
    private double bondRate; //债券利息
    private double bondRateAmount;//票面金额
    private  int payInterestNum; //付息次数Id
    private  String payInterest ; //付息次数
    private String fundId; //基金Id
    private String accountId; //账户Id
    private int securityPeriodFlag; //是否导入初期数据
    private int securitiesNum; //债券数量
    private String dateTime; //统计日期
    private double interest;

    public BondInterest() {
    }

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

    public String getPayInterest() {
        return payInterest;
    }

    public void setPayInterest(String payInterest) {
        this.payInterest = payInterest;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public BondInterest(String securitiesId, String bondName, String drawStartDate, String drawEndDate, int bondType, double parRate, double bondRate, double bondRateAmount, int payInterestNum, String payInterest, String fundId, String accountId, int securityPeriodFlag, int securitiesNum, String dateTime, double interest) {
        this.securitiesId = securitiesId;
        this.bondName = bondName;
        this.drawStartDate = drawStartDate;
        this.drawEndDate = drawEndDate;
        this.bondType = bondType;
        this.parRate = parRate;
        this.bondRate = bondRate;
        this.bondRateAmount = bondRateAmount;
        this.payInterestNum = payInterestNum;
        this.payInterest = payInterest;
        this.fundId = fundId;
        this.accountId = accountId;
        this.securityPeriodFlag = securityPeriodFlag;
        this.securitiesNum = securitiesNum;
        this.dateTime = dateTime;
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "BondInterest{" +
                "securitiesId='" + securitiesId + '\'' +
                ", bondName='" + bondName + '\'' +
                ", drawStartDate='" + drawStartDate + '\'' +
                ", drawEndDate='" + drawEndDate + '\'' +
                ", bondType=" + bondType +
                ", parRate=" + parRate +
                ", bondRate=" + bondRate +
                ", bondRateAmount=" + bondRateAmount +
                ", payInterestNum=" + payInterestNum +
                ", payInterest='" + payInterest + '\'' +
                ", fundId='" + fundId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", securityPeriodFlag=" + securityPeriodFlag +
                ", securitiesNum=" + securitiesNum +
                ", dateTime='" + dateTime + '\'' +
                ", interest=" + interest +
                '}';
    }
}

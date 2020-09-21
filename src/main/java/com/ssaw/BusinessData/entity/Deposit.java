package com.ssaw.BusinessData.entity;

/**
*@program:TescComment
*@Eescription:存款业务实体类
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
public class Deposit {
    private String depositId;//存款业务Id
    private String fundId;//基金Id
    private String outAccountId;//流出现金账户Id
    private String outAccountName;//流出现金账户名称
    private String inAccountId;//流入现金账户Id
    private String inAccountName;//流入现金账户名称
    private final int directionOfMoney=1;//资金调拨方向1代表流入-1代表流出
    private String businessDate;//业务时间
    private int businessType;//业务类型 1代表定期三天2代表七天3代表活期
    private double money;//存款金额
    private double interest;//所含利息
    private String endDate;//存款业务到期时间
    private  int flag;//到期办理标志0未办理1已办理
    private String depositDesc;//备注

    public Deposit() {
    }

    public Deposit(String depositId, String fundId, String outAccountId, String outAccountName, String inAccountId, String inAccountName, String businessDate, int businessType, double money, double interest, String endDate, int flag, String depositDesc) {
        this.depositId = depositId;
        this.fundId = fundId;
        this.outAccountId = outAccountId;
        this.outAccountName = outAccountName;
        this.inAccountId = inAccountId;
        this.inAccountName = inAccountName;
        this.businessDate = businessDate;
        this.businessType = businessType;
        this.money = money;
        this.interest = interest;
        this.endDate = endDate;
        this.flag = flag;
        this.depositDesc = depositDesc;
    }

    public Deposit(String depositId, String fundId, String outAccountId, String inAccountId, String businessDate, int businessType, double money, double interest, String endDate, int flag, String depositDesc) {
        this.depositId = depositId;
        this.fundId = fundId;
        this.outAccountId = outAccountId;
        this.inAccountId = inAccountId;
        this.businessDate = businessDate;
        this.businessType = businessType;
        this.money = money;
        this.interest = interest;
        this.endDate = endDate;
        this.flag = flag;
        this.depositDesc = depositDesc;
    }

    public String getDepositId() {
        return depositId;
    }

    public void setDepositId(String depositId) {
        this.depositId = depositId;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getOutAccountId() {
        return outAccountId;
    }

    public void setOutAccountId(String outAccountId) {
        this.outAccountId = outAccountId;
    }

    public String getOutAccountName() {
        return outAccountName;
    }

    public void setOutAccountName(String outAccountName) {
        this.outAccountName = outAccountName;
    }

    public String getInAccountId() {
        return inAccountId;
    }

    public void setInAccountId(String inAccountId) {
        this.inAccountId = inAccountId;
    }

    public String getInAccountName() {
        return inAccountName;
    }

    public void setInAccountName(String inAccountName) {
        this.inAccountName = inAccountName;
    }

    public int getDirectionOfMoney() {
        return directionOfMoney;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getDepositDesc() {
        return depositDesc;
    }

    public void setDepositDesc(String depositDesc) {
        this.depositDesc = depositDesc;
    }

    @Override
    public String toString() {
        return "Deposit{" +
                "depositId='" + depositId + '\'' +
                ", fundId='" + fundId + '\'' +
                ", outAccountId='" + outAccountId + '\'' +
                ", inAccountId='" + inAccountId + '\'' +
                ", directionOfMoney=" + directionOfMoney +
                ", businessDate='" + businessDate + '\'' +
                ", businessType=" + businessType +
                ", money=" + money +
                ", interest=" + interest +
                ", endDate='" + endDate + '\'' +
                ", flag=" + flag +
                ", depositDesc='" + depositDesc + '\'' +
                '}';
    }
}

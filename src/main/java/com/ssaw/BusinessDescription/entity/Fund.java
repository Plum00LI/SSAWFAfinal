package com.ssaw.BusinessDescription.entity;
/**
 * create by: 曾钦辉
 * description: 基金信息实体类
 * create time: 2020/9/1 9:46
 *
  * @Param: null
 * @return
 */

/**
 * 表名 fund
 * 实体类字段
 * fundId	varchar2(50)	PK  基金代码 (每个基金都有唯一的代码)
 * fundName	Varchar2(50)	基金名称
 * fundType	number(1)	1代表开放式基金 2代表封闭式基金
 * managerId	varchar2(50)	Fk  管理人id  来自管理人表
 * trusteeBlank TRUSTEEID	Varchar2(50)	FK  托管人id  来自托管人表
 * initNetWorth	Number(14,2)	初始净值
 * sizeOfThe	Number(14,2)	基金规模
 * managerRate	Number(10,4)	管理人费率 年利率  ?/100
 * hostingRate	Number(10,4)	托管人费率  年利率  ?/100
 * provisionDays	Number(1)	1=360天；2=365天；3=366天
 * setUpDate	date	基金成立时间
 * fundDesc	varchar2(100)	备注
 * accountId  	varchar2(50)	FK 账户信息表
 *
 */
public class Fund {
    private String fundId;
    private String fundName;
    private double fundType;
    private String managerId;
    private String trusteeId;
    private double initNetWorth;
    private double sizeOfThe;
    private double managerRate;
    private double hostingRate;
    private double provisionDays;
    private String setUpDate;
    private String fundDesc;
    private String accountId="000899001";
    private String managerCompany;
    private String trusteeCompany;

    public Fund() {
    }

    public String getManagerCompany() {
        return managerCompany;
    }

    public void setManagerCompany(String managerCompany) {
        this.managerCompany = managerCompany;
    }

    public String getTrusteeCompany() {
        return trusteeCompany;
    }

    public void setTrusteeCompany(String trusteeCompany) {
        this.trusteeCompany = trusteeCompany;
    }

    public Fund(String fundId, String fundName, double fundType, String managerId, String trusteeId, double initNetWorth, double sizeOfThe,
                double managerRate, double hostingRate, double provisionDays, String setUpDate, String fundDesc, String accountId) {
        this.fundId = fundId;
        this.fundName = fundName;
        this.fundType = fundType;
        this.managerId = managerId;
        this.trusteeId = trusteeId;
        this.initNetWorth = initNetWorth;
        this.sizeOfThe = sizeOfThe;
        this.managerRate = managerRate;
        this.hostingRate = hostingRate;
        this.provisionDays = provisionDays;
        this.setUpDate = setUpDate;
        this.fundDesc = fundDesc;
        this.accountId = accountId;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public double getFundType() {
        return fundType;
    }

    public void setFundType(double fundType) {
        this.fundType = fundType;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getTrusteeId() {
        return trusteeId;
    }

    public void setTrusteeId(String trusteeId) {
        this.trusteeId = trusteeId;
    }

    public double getInitNetWorth() {
        return initNetWorth;
    }

    public void setInitNetWorth(double initNetWorth) {
        this.initNetWorth = initNetWorth;
    }

    public double getSizeOfThe() {
        return sizeOfThe;
    }

    public void setSizeOfThe(double sizeOfThe) {
        this.sizeOfThe = sizeOfThe;
    }

    public double getManagerRate() {
        return managerRate;
    }

    public void setManagerRate(double managerRate) {
        this.managerRate = managerRate;
    }

    public double getHostingRate() {
        return hostingRate;
    }

    public void setHostingRate(double hostingRate) {
        this.hostingRate = hostingRate;
    }

    public double getProvisionDays() {
        return provisionDays;
    }

    public void setProvisionDays(double provisionDays) {
        this.provisionDays = provisionDays;
    }

    public String getSetUpDate() {
        return setUpDate;
    }

    public void setSetUpDate(String setUpDate) {
        this.setUpDate = setUpDate;
    }

    public String getFundDesc() {
        return fundDesc;
    }

    public void setFundDesc(String fundDesc) {
        this.fundDesc = fundDesc;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "Fund{" +
                "fundId='" + fundId + '\'' +
                ", fundName='" + fundName + '\'' +
                ", fundType=" + fundType +
                ", managerId='" + managerId + '\'' +
                ", trusteeId='" + trusteeId + '\'' +
                ", initNetWorth=" + initNetWorth +
                ", sizeOfThe=" + sizeOfThe +
                ", managerRate=" + managerRate +
                ", hostingRate=" + hostingRate +
                ", provisionDays=" + provisionDays +
                ", setUpDate='" + setUpDate + '\'' +
                ", fundDesc='" + fundDesc + '\'' +
                ", accountId='" + accountId + '\'' +
                '}';
    }
}

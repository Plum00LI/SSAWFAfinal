package com.ssaw.DayEndProcessing.entity;

public class TwoFees {
    private String fundId;
    private String fundName; // 基金名
    private int fundType; //基金类型
    private String managerId; //管理人Id
    private String trusteeBlank; //托管人Id
    private double managerRate; //管理人费率
    private double hostingRate ; //托管人费率
    private double management;  //管理费利息
    private double custody;  //托管费利息
    private double propertyNetWorth; //资产净值
    private String valueStatisticsDate;
    private String accountId;
    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public TwoFees() {
    }

    public int getFundType() {
        return fundType;
    }

    public void setFundType(int fundType) {
        this.fundType = fundType;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getTrusteeBlank() {
        return trusteeBlank;
    }

    public void setTrusteeBlank(String trusteeBlank) {
        this.trusteeBlank = trusteeBlank;
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

    public double getManagement() {
        return management;
    }

    public void setManagement(double management) {
        this.management = management;
    }

    public double getCustody() {
        return custody;
    }

    public void setCustody(double custody) {
        this.custody = custody;
    }

    public double getPropertyNetWorth() {
        return propertyNetWorth;
    }

    public void setPropertyNetWorth(double propertyNetWorth) {
        this.propertyNetWorth = propertyNetWorth;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public String getValueStatisticsDate() {
        return valueStatisticsDate;
    }

    public void setValueStatisticsDate(String valueStatisticsDate) {
        this.valueStatisticsDate = valueStatisticsDate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public TwoFees(String fundId, String fundName, int fundType, String managerId, String trusteeBlank, double managerRate, double hostingRate, double management, double custody, double propertyNetWorth, String valueStatisticsDate, String accountId) {
        this.fundId = fundId;
        this.fundName = fundName;
        this.fundType = fundType;
        this.managerId = managerId;
        this.trusteeBlank = trusteeBlank;
        this.managerRate = managerRate;
        this.hostingRate = hostingRate;
        this.management = management;
        this.custody = custody;
        this.propertyNetWorth = propertyNetWorth;
        this.valueStatisticsDate = valueStatisticsDate;
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "TwoFees{" +
                "fundId='" + fundId + '\'' +
                ", fundName='" + fundName + '\'' +
                ", fundType=" + fundType +
                ", managerId='" + managerId + '\'' +
                ", trusteeBlank='" + trusteeBlank + '\'' +
                ", managerRate=" + managerRate +
                ", hostingRate=" + hostingRate +
                ", management=" + management +
                ", custody=" + custody +
                ", propertyNetWorth=" + propertyNetWorth +
                ", valueStatisticsDate='" + valueStatisticsDate + '\'' +
                ", accountId='" + accountId + '\'' +
                '}';
    }
}
package com.ssaw.DayEndProcessing.entity;
/**
 * 收益计提-两费
 * @type 实体类
 * @author fusaiying
 * @date 2020-09-010
 * @version 1.0
 */
public class TwoFees {
    private String fundId;
    private String fundName; //基金名
    private int fundType; //基金类型
    private String managerId; //管理人Id
    private String trusteeBlank; //托管人Id
    private double managerRate; //管理人费率
    private double hostingRate ; //托管人费率
    private double management;  //管理费利息
    private double custody;  //托管费利息
    private double cost; //资产净值
    private String valueStatisticsDate;
    private String accountId;
    private double managementMoney;
    private double custodyMoney;
    private double marketValue;

    public TwoFees() {
    }

    public TwoFees(String fundId, String fundName, int fundType, String managerId, String trusteeBlank, double managerRate, double hostingRate, double management, double custody, double cost, String valueStatisticsDate, String accountId, double managementMoney, double custodyMoney, double marketValue) {
        this.fundId = fundId;
        this.fundName = fundName;
        this.fundType = fundType;
        this.managerId = managerId;
        this.trusteeBlank = trusteeBlank;
        this.managerRate = managerRate;
        this.hostingRate = hostingRate;
        this.management = management;
        this.custody = custody;
        this.cost = cost;
        this.valueStatisticsDate = valueStatisticsDate;
        this.accountId = accountId;
        this.managementMoney = managementMoney;
        this.custodyMoney = custodyMoney;
        this.marketValue = marketValue;
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

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
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

    public double getManagementMoney() {
        return managementMoney;
    }

    public void setManagementMoney(double managementMoney) {
        this.managementMoney = managementMoney;
    }

    public double getCustodyMoney() {
        return custodyMoney;
    }

    public void setCustodyMoney(double custodyMoney) {
        this.custodyMoney = custodyMoney;
    }

    public double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(double marketValue) {
        this.marketValue = marketValue;
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
                ", cost=" + cost +
                ", valueStatisticsDate='" + valueStatisticsDate + '\'' +
                ", accountId='" + accountId + '\'' +
                ", managementMoney=" + managementMoney +
                ", custodyMoney=" + custodyMoney +
                ", marketValue=" + marketValue +
                '}';
    }
}
package com.ssaw.DayEndProcessing.entity;

/**
 * create by: 佘高鹏
 * description: TODO
 * create time: 2020/9/1 9:43
 * updata time: 2020/9/3 16:03
 * version number 2.0
  * @Param: 净值统计类
 * @return 
 */

/**
 * 表名	valueStatistics净值统计
 * 表字段	数据类型	表字段名称	备注
 *     valueStatisticsDate varchar2(50) 时间 联合主键,
 *     fundId              varchar2(50) 基金ID 联合主键,
 *     projectId           Number (5) 项目编号  联合主键 体现层次分级关系/例如股票,
 *     projectName         varchar2(50)项目名称	例如证券,,
 *     projectCode         varchar2(50) 项目代码/账户号,
 *     quantityint         number(12,2) 股数		证券库存统计,
 *     peice               number(12,3) 行情		行情数据,
 *     cost                number(12,3)成本		资产估值,
 *     marketValue         number(12,3) 市值,
 *     valuation           number(12,3)估值增值,
 *     projectFatherId     Number (5)父项目编号	体现层次分级关系/例如证券
 */
public class ValueStatistics {
    private String valueStatisticsDate;
    private String fundId ; // 来自基金表
    private int projectId;
    private String projectName;
    private String projectCode;
    private Double quantityint;
    private Double peice;
    private Double cost;
    private Double marketValue;
    private Double valuation;
    private int projectFatherId;


    public ValueStatistics() {
    }

    public ValueStatistics(Double cost) {
        this.cost = cost;
    }

    public ValueStatistics(int projectId, String projectName, String projectCode, Double quantityint, Double peice, Double cost, Double marketValue, Double valuation, int projectFatherId) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.quantityint = quantityint;
        this.peice = peice;
        this.cost = cost;
        this.marketValue = marketValue;
        this.valuation = valuation;
        this.projectFatherId = projectFatherId;
    }

    public ValueStatistics(String valueStatisticsDate, String fundId, int projectId, String projectName, String projectCode, Double quantityint, Double peice, Double cost, Double marketValue, Double valuation, int projectFatherId) {
        this.valueStatisticsDate = valueStatisticsDate;
        this.fundId = fundId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.quantityint = quantityint;
        this.peice = peice;
        this.cost = cost;
        this.marketValue = marketValue;
        this.valuation = valuation;
        this.projectFatherId = projectFatherId;
    }

    public ValueStatistics(String valueStatisticsDate, String fundId, int projectId, String projectName, String projectCode, Double cost, int projectFatherId) {
        this.valueStatisticsDate = valueStatisticsDate;
        this.fundId = fundId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.cost = cost;
        this.projectFatherId = projectFatherId;
    }

    public ValueStatistics(String valueStatisticsDate, String fundId, int projectId, String projectName, String projectCode, int projectFatherId) {
        this.valueStatisticsDate = valueStatisticsDate;
        this.fundId = fundId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.projectFatherId = projectFatherId;
    }

    public ValueStatistics(String valueStatisticsDate, String fundId, int projectId, String projectName, Double cost, int projectFatherId) {
        this.valueStatisticsDate = valueStatisticsDate;
        this.fundId = fundId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.cost = cost;
        this.projectFatherId = projectFatherId;
    }

    public ValueStatistics(String valueStatisticsDate, String fundId, int projectId, String projectName, int projectFatherId) {
        this.valueStatisticsDate = valueStatisticsDate;
        this.fundId = fundId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectFatherId = projectFatherId;
    }

    public ValueStatistics(String fundId, int projectId, String projectName, int projectFatherId) {
        this.fundId = fundId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectFatherId = projectFatherId;
    }

    public ValueStatistics(String fundId, int projectId, String projectName, String projectCode, Double cost, int projectFatherId) {
        this.fundId = fundId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.cost = cost;
        this.projectFatherId = projectFatherId;
    }

    public ValueStatistics(String fundId, int projectId, String projectName, Double cost, int projectFatherId) {
        this.fundId = fundId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.cost = cost;
        this.projectFatherId = projectFatherId;
    }

    public ValueStatistics(String fundId, int projectId, String projectName, String projectCode, int projectFatherId) {
        this.fundId = fundId;
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectCode = projectCode;
        this.projectFatherId = projectFatherId;
    }

    public String getValueStatisticsDate() {
        return valueStatisticsDate;
    }

    public void setValueStatisticsDate(String valueStatisticsDate) {
        this.valueStatisticsDate = valueStatisticsDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getFundId() {
        return fundId;
    }

    public void setFundId(String fundId) {
        this.fundId = fundId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }

    public Double getQuantityint() {
        return quantityint;
    }

    public void setQuantityint(Double quantityint) {
        this.quantityint = quantityint;
    }

    public Double getPeice() {
        return peice;
    }

    public void setPeice(Double peice) {
        this.peice = peice;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Double getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Double marketValue) {
        this.marketValue = marketValue;
    }

    public Double getValuation() {
        return valuation;
    }

    public void setValuation(Double valuation) {
        this.valuation = valuation;
    }

    public int getProjectFatherId() {
        return projectFatherId;
    }

    public void setProjectFatherId(int projectFatherId) {
        this.projectFatherId = projectFatherId;
    }

    @Override
    public String toString() {
        return "ValueStatistics{" +
                "valueStatisticsDate='" + valueStatisticsDate + '\'' +
                ", fundId='" + fundId + '\'' +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectCode='" + projectCode + '\'' +
                ", quantityint=" + quantityint +
                ", peice=" + peice +
                ", cost=" + cost +
                ", marketValue=" + marketValue +
                ", valuation=" + valuation +
                ", projectFatherId=" + projectFatherId +
                '}';
    }
}

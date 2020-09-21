package com.ssaw.InventoryManagement.entity;

/**
 * @program:现金应收应付库存模块
 * @Description:实体类
 * @author:孙浩
 * @create:2020-09-01
 */

public class CashClosedPayInventory {
    private String cashClosedPayInventoryId;//现金应收应付库存Id（隐藏字段）
    private String businessDate;//业务日期
    private String accountId;//账户ID
    private String accountName;//现金账户名称
    private String fundId;//基金ID（隐藏字段）
    private String fundName;//基金名称
    private int businessType;//业务类型  1.管理费  2.托管费  3.存款利息  4.申购赎回费
    private int businessStatus;//业务状态   1.流入  -1.流出
    private int initialSigns;//期初标志  1.是   0.否
    private Double totalMoney;//总金额

    public CashClosedPayInventory() {
    }

    public CashClosedPayInventory(String cashClosedPayInventoryId, String businessDate, String accountId, String accountName, String fundId,
                                  String fundName, int businessType, int businessStatus, int initialSigns, Double totalMoney) {
        this.cashClosedPayInventoryId = cashClosedPayInventoryId;
        this.businessDate = businessDate;
        this.accountId = accountId;
        this.accountName = accountName;
        this.fundId = fundId;
        this.fundName = fundName;
        this.businessType = businessType;
        this.businessStatus = businessStatus;
        this.initialSigns = initialSigns;
        this.totalMoney = totalMoney;
    }



    public CashClosedPayInventory(String fundId, String accountId, int businessType, Double totalMoney
            , String businessDate,int initialSigns, int businessStatus, String accountName) {
            this.fundId = fundId;
            this.accountId = accountId;
            this.businessType = businessType;
            this.totalMoney = totalMoney;
            this.businessDate = businessDate;
            this.initialSigns = initialSigns;
            this.businessStatus = businessStatus;
            this.accountName = accountName;
        }


    public String getCashClosedPayInventoryId() {
        return cashClosedPayInventoryId;
    }

    public void setCashClosedPayInventoryId(String cashClosedPayInventoryId) {
        this.cashClosedPayInventoryId = cashClosedPayInventoryId;
    }

    public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
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

    public int getBusinessType() {
        return businessType;
    }

    public void setBusinessType(int businessType) {
        this.businessType = businessType;
    }

    public int getBusinessStatus() {
        return businessStatus;
    }

    public void setBusinessStatus(int businessStatus) {
        this.businessStatus = businessStatus;
    }

    public int getInitialSigns() {
        return initialSigns;
    }

    public void setInitialSigns(int initialSigns) {
        this.initialSigns = initialSigns;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    @Override
    public String toString() {
        return "现金应收应付库存模块{" +
                "现金应收应付库存ID='" + cashClosedPayInventoryId + '\'' +
                ", 业务日期='" + businessDate + '\'' +
                ", 账户ID='" + accountId + '\'' +
                ", 现金账户名称='" + accountName + '\'' +
                ", 基金ID=" + fundId +
                ", 业务类型=" + businessType +
                ", 业务状态=" + businessStatus +
                ", 期初标志=" + initialSigns +
                ", 总金额=" + totalMoney +
                '}';
    }
}

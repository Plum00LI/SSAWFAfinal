package com.ssaw.CashManagement.entity;

/**
*@program:TescComment
*@Eescription:划款指令实体类
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
public class TransferMoney {
    private String transferMoneyId;//划款指令Id
    private String inAccountId;//划款到的账户
    private String inBlankName;//划款到的账户的开户银行
    private String crossSectionDate;//划款时间
    private String accountingDate;//到账时间
    private double money;//划款金额
    private String outAccount;//划款的账户
    private String outBlankName;//划款的账户的开户银行
    private String foundId;//基金Id
    private String purpose;//划款的用途
    private String inAccountName;//划款到的账户的名称
    private String outAccountName;//划款的账户的名称
    private Integer orderCheque;            //是否显示抬头项
    private Integer auditor;                //是否显示审核人
    private String htitle;                  //抬头名称
    private String btitle;	                //抬头说明信息

    public TransferMoney() {
    }

    public TransferMoney(String transferMoneyId, String inAccountId, String inBlankName, String crossSectionDate, String accountingDate, double money, String outAccount, String outBlankName, String foundId, String purpose, String inAccountName, String outAccountName, Integer orderCheque, Integer auditor, String htitle, String btitle) {
        this.transferMoneyId = transferMoneyId;
        this.inAccountId = inAccountId;
        this.inBlankName = inBlankName;
        this.crossSectionDate = crossSectionDate;
        this.accountingDate = accountingDate;
        this.money = money;
        this.outAccount = outAccount;
        this.outBlankName = outBlankName;
        this.foundId = foundId;
        this.purpose = purpose;
        this.inAccountName = inAccountName;
        this.outAccountName = outAccountName;
        this.orderCheque = orderCheque;
        this.auditor = auditor;
        this.htitle = htitle;
        this.btitle = btitle;
    }

    public TransferMoney(String transferMoneyId, String inAccountId, String inBlankName, String crossSectionDate, String accountingDate, double money, String outAccount, String outBlankName, String foundId, String purpose, String inAccountName, String outAccountName) {
        this.transferMoneyId = transferMoneyId;
        this.inAccountId = inAccountId;
        this.inBlankName = inBlankName;
        this.crossSectionDate = crossSectionDate;
        this.accountingDate = accountingDate;
        this.money = money;
        this.outAccount = outAccount;
        this.outBlankName = outBlankName;
        this.foundId = foundId;
        this.purpose = purpose;
        this.inAccountName = inAccountName;
        this.outAccountName = outAccountName;
    }

    public TransferMoney(String transferMoneyId, String inAccountId, String inBlankName, String crossSectionDate, String accountingDate, double money, String outAccount, String outBlankName, String foundId, String purpose) {
        this.transferMoneyId = transferMoneyId;
        this.inAccountId = inAccountId;
        this.inBlankName = inBlankName;
        this.crossSectionDate = crossSectionDate;
        this.accountingDate = accountingDate;
        this.money = money;
        this.outAccount = outAccount;
        this.outBlankName = outBlankName;
        this.foundId = foundId;
        this.purpose = purpose;
    }

    public String getTransferMoneyId() {
        return transferMoneyId;
    }

    public void setTransferMoneyId(String transferMoneyId) {
        this.transferMoneyId = transferMoneyId;
    }

    public String getInAccountId() {
        return inAccountId;
    }

    public void setInAccountId(String inAccountId) {
        this.inAccountId = inAccountId;
    }

    public String getInBlankName() {
        return inBlankName;
    }

    public void setInBlankName(String inBlankName) {
        this.inBlankName = inBlankName;
    }

    public String getCrossSectionDate() {
        return crossSectionDate;
    }

    public void setCrossSectionDate(String crossSectionDate) {
        this.crossSectionDate = crossSectionDate;
    }

    public String getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(String accountingDate) {
        this.accountingDate = accountingDate;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getOutAccount() {
        return outAccount;
    }

    public void setOutAccount(String outAccount) {
        this.outAccount = outAccount;
    }

    public String getOutBlankName() {
        return outBlankName;
    }

    public void setOutBlankName(String outBlankName) {
        this.outBlankName = outBlankName;
    }

    public String getFoundId() {
        return foundId;
    }

    public void setFoundId(String foundId) {
        this.foundId = foundId;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getInAccountName() {
        return inAccountName;
    }

    public void setInAccountName(String inAccountName) {
        this.inAccountName = inAccountName;
    }

    public String getOutAccountName() {
        return outAccountName;
    }

    public void setOutAccountName(String outAccountName) {
        this.outAccountName = outAccountName;
    }

    public Integer getOrderCheque() {
        return orderCheque;
    }

    public void setOrderCheque(Integer orderCheque) {
        this.orderCheque = orderCheque;
    }

    public Integer getAuditor() {
        return auditor;
    }

    public void setAuditor(Integer auditor) {
        this.auditor = auditor;
    }

    public String getHtitle() {
        return htitle;
    }

    public void setHtitle(String htitle) {
        this.htitle = htitle;
    }

    public String getBtitle() {
        return btitle;
    }

    public void setBtitle(String btitle) {
        this.btitle = btitle;
    }

    @Override
    public String toString() {
        return "TransferMoney{" +
                "transferMoneyId='" + transferMoneyId + '\'' +
                ", inAccountId='" + inAccountId + '\'' +
                ", inBlankName='" + inBlankName + '\'' +
                ", crossSectionDate='" + crossSectionDate + '\'' +
                ", accountingDate='" + accountingDate + '\'' +
                ", money=" + money +
                ", outAccount='" + outAccount + '\'' +
                ", outBlankName='" + outBlankName + '\'' +
                ", foundId='" + foundId + '\'' +
                ", purpose='" + purpose + '\'' +
                '}';
    }
}

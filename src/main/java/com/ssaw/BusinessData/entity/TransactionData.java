package com.ssaw.BusinessData.entity;
/**
 * create by: 曾钦辉
 * description: 交易数据实体类
 * create time: 2020/9/1 10:22
 *
  * @Param: null
 * @return
 */

/**表名transactionData
 * transactionDataId	Varchar2(50)	PK 交易的单子号(主键) T2020083100001
 * dateTime	date	成交日期(交易成交的日期)
 * settlementDate	date	结算日期(交易结算的日期)
 * fundId	Varchar2(50)	FK 基金代码来自基金表
 * securitiesId	Varchar2(50)	FK 证券编号 （证券表的ID）
 * brokersId	Varchar2(50)	FK 券商ID(引用券商表的券商的ID)
 * seateId	Varchar2(50)	FK 席位Id(交易席位的Id)
 * mode	Number(1)	交易方式(1买入、2卖出，3分红，4送股)
 * status	Number(1)	交易状态（0未结算，1已结算）
 * price	number(14,2)	交易价格(单价)
 * num	number(14,2)	交易数量
 * accountId	Varchar2(50)	FK 来自现金账户表
 * netReceipts	number(14,2)	实收金额
 * totalSum	number(14,2)	交易总金额
 * flag	number(14,2)	交易标识,1流入，-1流出
 * commission	number(14,2)	佣金费用（券商）
 * transfer	number(14,2)	过户费（交易所）
 * brokerage	number(14,2)	经手费（交易所）
 * stamp	number(14,2)	印花税（上交国家的税）
 * management	number(14,2)	征管费（上交国家的税）
 * security	number(14,2)	证券利息
 * desc	varchar2(50)	备注
 */
public class TransactionData {
    private String transactionDataId;         //PK 交易的单子号(主键) T2020083100001
    private String accountName;                //现金账户
    private String securitiesName;                //证券名称
    private Double price;           //交易价格(单价)
    private Double num;             //交易数量
    private Double netReceipts;        //实收金额
    private Double totalSum;        //交易总金额
    private int transactionDataMode;//交易方式(1买入、2卖出，3分红，4送股)
    private String dateTime;        //交易日期(交易成交的日期)
    private String settlementDate;  //结算日期(交易结算的日期)
    private int status;             //交易状态（0未结算，1已结算）
    private String fundId;          //FK 基金代码   来自基金表
    private String fundName;        //基金名称
    private String securitiesId;    //FK 证券编号ID （证券表的ID）
    private String brokersId;       //FK 券商ID(引用券商表的券商的ID)
    private String brokersName;                  //券商名称
    private String seateId;         //FK 席位Id(交易席位的Id)
    private String seateName;                //席位名称
    private String accountId;        //FK 来自现金账户ID
    private String blankName;                 //银行名称
    private int flag;              //交易标识,1流入，-1流出
    private Double commission;        //佣金费用（券商）
    private Double transfer;        //过户费（交易所）
    private Double brokerage;        //经手费（交易所）
    private Double stamp;            //印花税（上交国家的税）
    private Double management;        //征管费（上交国家的税）
    private Double security;        //证券利息
    private String transactionDataDesc;            //备注

    public TransactionData() {
    }

    public TransactionData(String transactionDataId, String accountName, String securitiesName, Double price, Double num, Double netReceipts, Double totalSum, int transactionDataMode, String dateTime, String settlementDate, int status, String fundId, String fundName, String securitiesId, String brokersId, String brokersName, String seateId, String seateName, String accountId, String blankName, int flag, Double commission,
                           Double transfer, Double brokerage, Double stamp, Double management, Double security, String transactionDataDesc) {
        this.transactionDataId = transactionDataId;
        this.accountName = accountName;
        this.securitiesName = securitiesName;
        this.price = price;
        this.num = num;
        this.netReceipts = netReceipts;
        this.totalSum = totalSum;
        this.transactionDataMode = transactionDataMode;
        this.dateTime = dateTime;
        this.settlementDate = settlementDate;
        this.status = status;
        this.fundId = fundId;
        this.fundName = fundName;
        this.securitiesId = securitiesId;
        this.brokersId = brokersId;
        this.brokersName = brokersName;
        this.seateId = seateId;
        this.seateName = seateName;
        this.accountId = accountId;
        this.blankName = blankName;
        this.flag = flag;
        this.commission = commission;
        this.transfer = transfer;
        this.brokerage = brokerage;
        this.stamp = stamp;
        this.management = management;
        this.security = security;
        this.transactionDataDesc = transactionDataDesc;
    }

    public String getTransactionDataId() {
        return transactionDataId;
    }

    public void setTransactionDataId(String transactionDataId) {
        this.transactionDataId = transactionDataId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getSecuritiesName() {
        return securitiesName;
    }

    public void setSecuritiesName(String securitiesName) {
        this.securitiesName = securitiesName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getNum() {
        return num;
    }

    public void setNum(Double num) {
        this.num = num;
    }

    public Double getNetReceipts() {
        return netReceipts;
    }

    public void setNetReceipts(Double netReceipts) {
        this.netReceipts = netReceipts;
    }

    public Double getTotalSum() {
        return totalSum;
    }

    public void setTotalSum(Double totalSum) {
        this.totalSum = totalSum;
    }

    public int getTransactionDataMode() {
        return transactionDataMode;
    }

    public void setTransactionDataMode(int transactionDataMode) {
        this.transactionDataMode = transactionDataMode;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getSecuritiesId() {
        return securitiesId;
    }

    public void setSecuritiesId(String securitiesId) {
        this.securitiesId = securitiesId;
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

    public String getSeateId() {
        return seateId;
    }

    public void setSeateId(String seateId) {
        this.seateId = seateId;
    }

    public String getSeateName() {
        return seateName;
    }

    public void setSeateName(String seateName) {
        this.seateName = seateName;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getBlankName() {
        return blankName;
    }

    public void setBlankName(String blankName) {
        this.blankName = blankName;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Double getCommission() {
        return commission;
    }

    public void setCommission(Double commission) {
        this.commission = commission;
    }

    public Double getTransfer() {
        return transfer;
    }

    public void setTransfer(Double transfer) {
        this.transfer = transfer;
    }

    public Double getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(Double brokerage) {
        this.brokerage = brokerage;
    }

    public Double getStamp() {
        return stamp;
    }

    public void setStamp(Double stamp) {
        this.stamp = stamp;
    }

    public Double getManagement() {
        return management;
    }

    public void setManagement(Double management) {
        this.management = management;
    }

    public Double getSecurity() {
        return security;
    }

    public void setSecurity(Double security) {
        this.security = security;
    }

    public String getTransactionDataDesc() {
        return transactionDataDesc;
    }

    public void setTransactionDataDesc(String transactionDataDesc) {
        this.transactionDataDesc = transactionDataDesc;
    }

    @Override
    public String toString() {
        return "TransactionData{" +
                "transactionDataId='" + transactionDataId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", securitiesName='" + securitiesName + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", netReceipts=" + netReceipts +
                ", totalSum=" + totalSum +
                ", transactionDataMode=" + transactionDataMode +
                ", dateTime='" + dateTime + '\'' +
                ", settlementDate='" + settlementDate + '\'' +
                ", status=" + status +
                ", fundId='" + fundId + '\'' +
                ", fundName='" + fundName + '\'' +
                ", securitiesId='" + securitiesId + '\'' +
                ", brokersId='" + brokersId + '\'' +
                ", brokersName='" + brokersName + '\'' +
                ", seateId='" + seateId + '\'' +
                ", seateName='" + seateName + '\'' +
                ", accountId='" + accountId + '\'' +
                ", blankName='" + blankName + '\'' +
                ", flag=" + flag +
                ", commission=" + commission +
                ", transfer=" + transfer +
                ", brokerage=" + brokerage +
                ", stamp=" + stamp +
                ", management=" + management +
                ", security=" + security +
                ", transactionDataDesc='" + transactionDataDesc + '\'' +
                '}';
    }
}

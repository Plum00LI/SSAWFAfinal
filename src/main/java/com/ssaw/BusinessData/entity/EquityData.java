package com.ssaw.BusinessData.entity;

/*0.*
 * @program:权益数据模块
 * @Description:实体类
 * @author:孙浩
 * @create:2020-09-01
 */

public class EquityData {
    private String equityDataId;//权益ID（隐藏字段）
    private String dateTime;//业务日期
    private String securityId;//证券Id
    private String securitiesName;//证券名称
    private String equitiesRecord;//权益登记日
    private String equitiesExright;//权益除权日
    private String receivedDate;//到账日期
    private int equitiesType;//权益类型  1.送股   2.分红
    private int proportion;//比例
    private int disposeStatus;//处理状态   0.未处理   1.已处理

    public EquityData() {
    }

    public EquityData(String equityDataId, String dateTime, String securityId, String securitiesName, String equitiesRecord,
                      String equitiesExright, String receivedDate, int equitiesType, int proportion, int disposeStatus) {
        this.equityDataId = equityDataId;
        this.dateTime = dateTime;
        this.securityId = securityId;
        this.securitiesName = securitiesName;
        this.equitiesRecord = equitiesRecord;
        this.equitiesExright = equitiesExright;
        this.receivedDate = receivedDate;
        this.equitiesType = equitiesType;
        this.proportion = proportion;
        this.disposeStatus = disposeStatus;
    }

    public EquityData(String equityDataId, String dateTime, String securityId, String equitiesRecord,
                      String equitiesExright, String receivedDate, int equitiesType, int proportion, int disposeStatus) {
        this.equityDataId = equityDataId;
        this.dateTime = dateTime;
        this.securityId = securityId;
        this.equitiesRecord = equitiesRecord;
        this.equitiesExright = equitiesExright;
        this.receivedDate = receivedDate;
        this.equitiesType = equitiesType;
        this.proportion = proportion;
        this.disposeStatus = disposeStatus;
    }

    public String getEquityDataId() {
        return equityDataId;
    }

    public void setEquityDataId(String equityDataId) {
        this.equityDataId = equityDataId;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getSecurityId() {
        return securityId;
    }

    public void setSecurityId(String securityId) {
        this.securityId = securityId;
    }

    public String getSecuritiesName() {
        return securitiesName;
    }

    public void setSecuritiesName(String securitiesName) {
        this.securitiesName = securitiesName;
    }

    /*public int getSecuritiesNum() {
        return securitiesNum;
    }

    public void setSecuritiesNum(int securitiesNum) {
        this.securitiesNum = securitiesNum;
    }*/

    public String getEquitiesRecord() {
        return equitiesRecord;
    }

    public void setEquitiesRecord(String equitiesRecord) {
        this.equitiesRecord = equitiesRecord;
    }

    public String getEquitiesExright() {
        return equitiesExright;
    }

    public void setEquitiesExright(String equitiesExright) {
        this.equitiesExright = equitiesExright;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }

    public int getEquitiesType() {
        return equitiesType;
    }

    public void setEquitiesType(int equitiesType) {
        this.equitiesType = equitiesType;
    }

    public int getProportion() {
        return proportion;
    }

    public void setProportion(int proportion) {
        this.proportion = proportion;
    }

    public int getDisposeStatus() {
        return disposeStatus;
    }

    public void setDisposeStatus(int disposeStatus) {
        this.disposeStatus = disposeStatus;
    }

    @Override
    public String toString() {
        return "权益数据模块{" +
                "权益ID=" + equityDataId +
                ", 业务日期='" + dateTime + '\'' +
                ", 证券ID=" + securityId +
                ", 登记日='" + equitiesRecord + '\'' +
                ", 除权日='" + equitiesExright + '\'' +
                ", 到账日='" + receivedDate + '\'' +
                ", 权益类型=" + equitiesType +
                ", 比例=" + proportion +
                ", 处理状态=" + disposeStatus +
                '}';
    }
}

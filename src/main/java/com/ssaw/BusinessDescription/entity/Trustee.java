package com.ssaw.BusinessDescription.entity;
/**
 * create by: 曾钦辉
 * description: 托管人实体类
 * create time: 2020/9/7 10:25
 *
  * @Param: null
 * @return
 */
public class Trustee {
//    trusteeId	                varchar2(50)                                not null ,           /*PK  托管人的ID*/
//    trusteeName	                Varchar2(50)                                not null ,           /*托管人名字*/
//    trusteeAddres	            Varchar2(100)                                not null ,      /*托管人地址*/
//    trusteeCompany	            Varchar2(50)                                not null ,       /*托管公司*/
//    trusteePhone	            Varchar2(50)                                not null ,       /*电话*/
//    trusteeFee	                number(16,2)                                not null ,           /*托管费率*/
//    trusteeDesc	                Varchar2(100)               /*备注*/
    private String trusteeId;
    private String  trusteeName;
    private String trusteeAddres;
    private String trusteeCompany;
    private String trusteePhone;
    private double trusteeFee;
    private String trusteeDesc;
    public Trustee(){}

    public Trustee(String trusteeName, String trusteeAddres,
                   String trusteeCompany, String trusteePhone, double trusteeFee, String trusteeDesc) {
        this.trusteeName = trusteeName;
        this.trusteeAddres = trusteeAddres;
        this.trusteeCompany = trusteeCompany;
        this.trusteePhone = trusteePhone;
        this.trusteeFee = trusteeFee;
        this.trusteeDesc = trusteeDesc;
    }

    public Trustee(String trusteeId, String trusteeName,
                   String trusteeAddres, String trusteeCompany, String trusteePhone, double trusteeFee, String trusteeDesc) {
        this.trusteeId = trusteeId;
        this.trusteeName = trusteeName;
        this.trusteeAddres = trusteeAddres;
        this.trusteeCompany = trusteeCompany;
        this.trusteePhone = trusteePhone;
        this.trusteeFee = trusteeFee;
        this.trusteeDesc = trusteeDesc;
    }

    public String getTrusteeId() {
        return trusteeId;
    }

    public void setTrusteeId(String trusteeId) {
        this.trusteeId = trusteeId;
    }

    public String getTrusteeName() {
        return trusteeName;
    }

    public void setTrusteeName(String trusteeName) {
        this.trusteeName = trusteeName;
    }

    public String getTrusteeAddres() {
        return trusteeAddres;
    }

    public void setTrusteeAddres(String trusteeAddres) {
        this.trusteeAddres = trusteeAddres;
    }

    public String getTrusteeCompany() {
        return trusteeCompany;
    }

    public void setTrusteeCompany(String trusteeCompany) {
        this.trusteeCompany = trusteeCompany;
    }

    public String getTrusteePhone() {
        return trusteePhone;
    }

    public void setTrusteePhone(String trusteePhone) {
        this.trusteePhone = trusteePhone;
    }

    public double getTrusteeFee() {
        return trusteeFee;
    }

    public void setTrusteeFee(double trusteeFee) {
        this.trusteeFee = trusteeFee;
    }

    public String getTrusteeDesc() {
        return trusteeDesc;
    }

    public void setTrusteeDesc(String trusteeDesc) {
        this.trusteeDesc = trusteeDesc;
    }

    @Override
    public String toString() {
        return "Trustee{" +
                "trusteeId='" + trusteeId + '\'' +
                ", trusteeName='" + trusteeName + '\'' +
                ", trusteeAddres='" + trusteeAddres + '\'' +
                ", trusteeCompany='" + trusteeCompany + '\'' +
                ", trusteePhone='" + trusteePhone + '\'' +
                ", trusteeFee=" + trusteeFee +
                ", trusteeDesc='" + trusteeDesc + '\'' +
                '}';
    }
}

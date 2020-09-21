package com.ssaw.BusinessDescription.entity;

import org.apache.ibatis.annotations.Mapper;

/**
 * create by: 曾钦辉
 * description: 管理人实体类
 * create time: 2020/9/7 15:55
 *
  * @Param: null
 * @return
 */
@Mapper
public class Manager {
//    managerId	                varchar2(50)                                not null ,           /* PK  管理人的ID*/
//    managerAge	                number(3)                                   not null ,              /*托管人地址*/
//    managerCompany	            varchar2(100)                               not null ,      /*所在公司*/
//    managerPhone	            varchar2(50)                                not null ,       /*管理人的电话*/
//    managerFee	                number(16,2)                                not null ,           /*管理费率*/
//    managerDesc	                Varchar2(100)               /*备注*/
    private String managerId;
    private double managerAge;
    private String managerCompany;
    private String managerPhone;
    private double managerFee;
    private String managerDesc;

    public Manager() {
    }

    public Manager(double managerAge, String managerCompany, String managerPhone, double managerFee, String managerDesc) {
        this.managerAge = managerAge;
        this.managerCompany = managerCompany;
        this.managerPhone = managerPhone;
        this.managerFee = managerFee;
        this.managerDesc = managerDesc;
    }

    public Manager(String managerId,
                   double managerAge, String managerCompany, String managerPhone, double managerFee, String managerDesc) {
        this.managerId = managerId;
        this.managerAge = managerAge;
        this.managerCompany = managerCompany;
        this.managerPhone = managerPhone;
        this.managerFee = managerFee;
        this.managerDesc = managerDesc;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public double getManagerAge() {
        return managerAge;
    }

    public void setManagerAge(double managerAge) {
        this.managerAge = managerAge;
    }

    public String getManagerCompany() {
        return managerCompany;
    }

    public void setManagerCompany(String managerCompany) {
        this.managerCompany = managerCompany;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    public double getManagerFee() {
        return managerFee;
    }

    public void setManagerFee(double managerFee) {
        this.managerFee = managerFee;
    }

    public String getManagerDesc() {
        return managerDesc;
    }

    public void setManagerDesc(String managerDesc) {
        this.managerDesc = managerDesc;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "managerId='" + managerId + '\'' +
                ", managerAge=" + managerAge +
                ", managerCompany='" + managerCompany + '\'' +
                ", managerPhone='" + managerPhone + '\'' +
                ", managerFee=" + managerFee +
                ", managerDesc='" + managerDesc + '\'' +
                '}';
    }
}

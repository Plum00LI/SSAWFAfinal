package com.ssaw.GlobalManagement.entity;

/**
 * UserInfo实体类
 * @type: entity
 * @version: v1.0
 * @author: plum
 * @date: 2020/09/02
 */
public class UserInfo {
    private int userId;
    private String userName;
    private String userPwd;
    private int roleId;
    private int status;
    private String userInfoDesc;

    public UserInfo() {
    }

    public UserInfo(int userId, String userName, String userPwd, int roleId, int status, String userInfoDesc) {
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
        this.roleId = roleId;
        this.status = status;
        this.userInfoDesc = userInfoDesc;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserInfoDesc() {
        return userInfoDesc;
    }

    public void setUserInfoDesc(String userInfoDesc) {
        this.userInfoDesc = userInfoDesc;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", roleId=" + roleId +
                ", status=" + status +
                ", userInfoDesc='" + userInfoDesc + '\'' +
                '}';
    }
}

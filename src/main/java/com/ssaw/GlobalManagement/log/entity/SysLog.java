package com.ssaw.GlobalManagement.log.entity;

/**
 * 日志对象
 * @type: entity
 * @version: 1.0
 * @author: plum
 * @date: 2020/09/21
 */
public class SysLog {
    private int ssawLogId;
    private String logByTime; //访问时间
    private String logByUser; //访问用户
    private String logByIp; //访问IP
    private String logByUrl; //访问URL
    private String logByRunTime; //执行时间
    private String logByClass; //访问方法
    private String logByRunName; //执行方法信息
    /**
     * 日志信息
     * @param logByTime 访问时间
     * @param logByUser 访问用户
     * @param logByIp 访问IP
     * @param logByUrl 访问URL
     * @param logByRunTime 执行时间
     * @param logByClass 访问方法
     */
    public SysLog(String logByTime, String logByUser, String logByIp, String logByUrl, String logByRunTime, String logByClass, String logByRunName) {
        this.logByTime = logByTime;
        this.logByUser = logByUser;
        this.logByIp = logByIp;
        this.logByUrl = logByUrl;
        this.logByRunTime = logByRunTime;
        this.logByClass = logByClass;
        this.logByRunName = logByRunName;
    }

    public SysLog(int ssawLogId, String logByTime, String logByUser, String logByIp, String logByUrl, String logByRunTime, String logByClass, String logByRunName) {
        this.ssawLogId = ssawLogId;
        this.logByTime = logByTime;
        this.logByUser = logByUser;
        this.logByIp = logByIp;
        this.logByUrl = logByUrl;
        this.logByRunTime = logByRunTime;
        this.logByClass = logByClass;
        this.logByRunName = logByRunName;
    }

    public SysLog() {
    }

    public int getSsawLogId() {
        return ssawLogId;
    }

    public void setSsawLogId(int ssawLogId) {
        this.ssawLogId = ssawLogId;
    }

    public String getLogByTime() {
        return logByTime;
    }

    public void setLogByTime(String logByTime) {
        this.logByTime = logByTime;
    }

    public String getLogByUser() {
        return logByUser;
    }

    public void setLogByUser(String logByUser) {
        this.logByUser = logByUser;
    }

    public String getLogByIp() {
        return logByIp;
    }

    public void setLogByIp(String logByIp) {
        this.logByIp = logByIp;
    }

    public String getLogByUrl() {
        return logByUrl;
    }

    public void setLogByUrl(String logByUrl) {
        this.logByUrl = logByUrl;
    }

    public String getLogByRunTime() {
        return logByRunTime;
    }

    public void setLogByRunTime(String logByRunTime) {
        this.logByRunTime = logByRunTime;
    }

    public String getLogByClass() {
        return logByClass;
    }

    public void setLogByClass(String logByClass) {
        this.logByClass = logByClass;
    }

    public String getLogByRunName() {
        return logByRunName;
    }

    public void setLogByRunName(String logByRunName) {
        this.logByRunName = logByRunName;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "ssawLogId=" + ssawLogId +
                ", logByTime='" + logByTime + '\'' +
                ", logByUser='" + logByUser + '\'' +
                ", logByIp='" + logByIp + '\'' +
                ", logByUrl='" + logByUrl + '\'' +
                ", logByRunTime='" + logByRunTime + '\'' +
                ", logByClass='" + logByClass + '\'' +
                ", logByRunName='" + logByRunName + '\'' +
                '}';
    }
}

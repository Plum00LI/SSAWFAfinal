package com.ssaw.GlobalManagement.entity;
/**
 * create by: 佘高鹏
 * description: TODO
 * 权限实体类
 * create time: 2020/9/7 15:36
 * version number 1.0
  * @Param: null
 * @return
 */
public class Endow {
    private int endowId;
    private int roleId;
    private int funId;

    public Endow(int endowId, int roleId, int funId) {
        this.endowId = endowId;
        this.roleId = roleId;
        this.funId = funId;
    }

    public Endow() {
    }

    public Endow(int roleId, int funId) {
        this.roleId = roleId;
        this.funId = funId;
    }

    public int getEndowId() {
        return endowId;
    }

    public void setEndowId(int endowId) {
        this.endowId = endowId;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getFunId() {
        return funId;
    }

    public void setFunId(int funId) {
        this.funId = funId;
    }

    @Override
    public String toString() {
        return "Endow{" +
                "endowId=" + endowId +
                ", roleId=" + roleId +
                ", funId=" + funId +
                '}';
    }
}

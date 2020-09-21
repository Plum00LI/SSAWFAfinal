package com.ssaw.GlobalManagement.entity;
/**
 * create by: 佘高鹏
 * description: TODO
 * 角色实体类
 * create time: 2020/9/7 15:34
 * version number 1.0
  * @Param: null
 * @return
 */
public class Role {
    private int     roleId;
    private String  roleName;
    private int  status;
    private String  statusRole;
    private String  roleDesc;

    public Role(int roleId, String roleName, String statusRole, String roleDesc) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.statusRole = statusRole;
        this.roleDesc = roleDesc;
    }

    public Role(int roleId, String roleName, int status, String roleDesc) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.status = status;
        this.roleDesc = roleDesc;
    }

    public Role(int roleId, String roleName, int status, String statusRole, String roleDesc) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.status = status;
        this.statusRole = statusRole;
        this.roleDesc = roleDesc;
    }

    public Role() {
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStatusRole() {
        return statusRole;
    }

    public void setStatusRole(String statusRole) {
        this.statusRole = statusRole;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}

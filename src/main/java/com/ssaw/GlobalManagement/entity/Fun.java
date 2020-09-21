package com.ssaw.GlobalManagement.entity;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 角色功能实体类
 * create time: 2020/9/7 15:30
 * version number 1.0
  * @Param: null
 * @return
 */
public class Fun {
    private int id;
    private String title;
    private boolean checked;
    private int endowId;
    private List<Fun> children=null;

    public Fun(int id, String title, boolean checked, int endowId, List<Fun> children) {
        this.id = id;
        this.title = title;
        this.checked = checked;
        this.endowId = endowId;
        this.children = children;
    }

    public Fun() {
    }

    public Fun(int id, String title, boolean checked) {
        this.id = id;
        this.title = title;
        this.checked = checked;
    }

    public Fun(int id, String title, boolean checked, int endowId) {
        this.id = id;
        this.title = title;
        this.checked = checked;
        this.endowId = endowId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<Fun> getChildren() {
        return children;
    }

    public void setChildren(List<Fun> children) {
        this.children = children;
    }

    public int getEndowId() {
        return endowId;
    }

    public void setEndowId(int endowId) {
        this.endowId = endowId;
    }

    @Override
    public String toString() {
        return "Fun{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", checked=" + checked +
                ", endowId=" + endowId +
                ", children=" + children +
                '}';
    }
}

package com.ssaw.GlobalManagement.entity;
/**
 * create by: 佘高鹏
 * description: TODO
 * 权限模块加载测试实体类
 * create time: 2020/9/5 10:33
 * version number 1.0
 * @Param: null
 * @return
 */

import java.util.List;

/**
 *     功能ID  主键  int    id;
 *     功能名       String  title;
 *     父功能ID  int    pid;
 *     引用地址     String  href;
 *     图标        String  icon;
 *     打开方式     String  target;
 *     子功能集合   List<EndowTest> child=null;
 */
public class FunTest {
    private  int    id;
    private  int    pid;
    private String  title;

    private String  href;
    private String  icon;
    private String  target;
    private List<FunTest> child=null;


    public FunTest() {
    }

    public FunTest(int id, int pid, String title, String href, String icon, String target, List<FunTest> child) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.href = href;
        this.icon = icon;
        this.target = target;
        this.child = child;
    }

    public FunTest(int id, int pid, String title, String href, String icon, String target) {
        this.id = id;
        this.pid = pid;
        this.title = title;
        this.href = href;
        this.icon = icon;
        this.target = target;
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

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }



    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public List<FunTest> getChild() {
        return child;
    }

    public void setChild(List<FunTest> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "FunTest{" +
                "id=" + id +
                ", pid=" + pid +
                ", title='" + title + '\'' +
                ", href='" + href + '\'' +
                ", icon='" + icon + '\'' +
                ", target='" + target + '\'' +
                ", child=" + child +
                '}';
    }
}

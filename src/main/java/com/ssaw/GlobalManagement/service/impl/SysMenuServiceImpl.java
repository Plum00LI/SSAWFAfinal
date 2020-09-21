package com.ssaw.GlobalManagement.service.impl;

import com.ssaw.GlobalManagement.service.FunTestService;
import com.ssaw.GlobalManagement.service.SysMenuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统菜单服务层接口实现类
 * @type: impl
 * @version: v1.0
 * @author: plum
 * @date: 2020/09/07
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    //导入菜单列表服务层
    @Resource
    FunTestService funTestService;
    //重写生成菜单数据方法
    @Override
    public Map<String, Object> menu() {
        //创建菜单Map 存放菜单数据
        Map<String, Object> map = new HashMap<>(16);
        //创建Home对象存放Map
        Map<String,Object> home = new HashMap<>(16);
        //创建Logo对象存放Map
        Map<String,Object> logo = new HashMap<>(16);
        //数据库查询菜单对象
        List menuList = funTestService.selectPidFun(0,1);
        //将查询出菜单放入菜单Map
        map.put("menuInfo", menuList);
        //往home中放入主页头栏title数据
        home.put("title","首页");
        //往home中放入主页欢迎页地址
        home.put("href","page/welcome");//控制器路由,自行定义
        //往logo中放入网页LOGO显示文本
        logo.put("title","FA SSAW");
        //往logo中放入网页LOGO文件路径
        logo.put("image","../images/logo.png");//静态资源文件路径,可使用默认的logo.png
        logo.put("href","./");
        //将home对象放入菜单Map
        map.put("homeInfo", home);
        //将logo对象放入菜单Map
        map.put("logoInfo", logo);
        //返回菜单Map对象
        return map;
    }
}

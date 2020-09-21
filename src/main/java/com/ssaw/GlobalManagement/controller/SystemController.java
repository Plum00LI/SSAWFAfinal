package com.ssaw.GlobalManagement.controller;

import com.ssaw.BusinessDescription.entity.Account;
import com.ssaw.BusinessDescription.service.AccountService;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.service.SysMenuService;
import com.ssaw.GlobalManagement.service.UserInfoService;
import com.ssaw.GlobalManagement.util.OperationType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("system")
public class SystemController {
    //注册UserInfoService服务
    @Resource
    UserInfoService userInfoService;
    //注册SysMenuService服务
    @Resource
    SysMenuService sysMenuService;
    //注册AccountService服务
    @Resource
    AccountService accountService;
    //checkLogin验证登录方法
    @RequestMapping("checkLogin")
    @OperLog(message = "用户登录",operation = OperationType.LOGIN)
    public Map<String,Object> checkLogin(String userName, String userPwd, String fundMsg, HttpServletRequest req){
        //创建存放回调对象的map
        Map<String, Object> map = new HashMap<>();
        //调用userInfoService服务isLogin方法检查登录
        int status = userInfoService.isLogin(userName, userPwd);
        //判断登录状态，不同操作
        if (status==1){
            //返回正常状态
            map.put("code",1);
            //创建Session对象，存放当此会话登录数据
            HttpSession session = req.getSession();
            //在session中放入当次登录用户信息
            session.setAttribute("userName",userName);
            System.out.println(fundMsg);
            String[] split = fundMsg.split(",");
            //在session中放入当次登录基金信息
            session.setAttribute("fundId",split[0]);
            //通过accountId查询account信息
            Account account = accountService.selectAccountById(split[1]);
            //在session中放入当次登录默认账户ID
            session.setAttribute("accountId",split[1]);
            //在session中放入当次登录默认账户名称
            session.setAttribute("accountName",account.getAccountName());

        }else {
            //返回异常状态
            map.put("code",0);
        }
        //返回回调数据
        return map;
    }
    //logoff注销Session，退出登录方法
    @RequestMapping("logoff")
    @OperLog(message = "用户注销",operation = OperationType.LOGOUT)
    public Map<String,Object> logoff(HttpServletRequest req){
        //从请求中拿出session对象
        HttpSession session = req.getSession(false);
        //移除session对象userName
        session.removeAttribute("userName");
        //移除session对象fundId
        session.removeAttribute("fundId");
        //创建回调数据map
        Map<String,Object> map = new HashMap<>();
        //存入状态码，1正常
        map.put("code",1);
        //存入回调信息
        map.put("msg","已退出，3秒后将转跳到登录页");
        //返回回调数据
        return map;
    }

    @RequestMapping("menu")
    //请求菜单数据方法
    public Map<String,Object> menu(){
        //返回菜单数据
        return sysMenuService.menu();
    }
}

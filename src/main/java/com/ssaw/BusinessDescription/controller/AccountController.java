package com.ssaw.BusinessDescription.controller;

import com.ssaw.BusinessDescription.entity.Account;
import com.ssaw.BusinessDescription.service.AccountService;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.DbUtil;
import com.ssaw.GlobalManagement.util.OperationType;
import com.ssaw.GlobalManagement.util.SysTableNameListUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:TescComment
 * @Eescription:Controller控制层
 * @author:黄庆
 * @Version:1.0
 * @create:2020-09-02
 */
@RestController
@RequestMapping("account")
public class AccountController {

    /**
     * 注入account服务层
     */
    @Resource
    AccountService accountService;

    @Resource
    DbUtil dbUtil;

    /**
     * 界面请求的信息数据控制层查询所有现金账户的方法
     * @param page 当前查询页数
     * @param limit 分页数据条目数
     * @param accountName 现金账户名称
     * @param blankName 银行名称
     * @return  界面要求的数据格式
     */
    @RequestMapping("selectAccount")
    @OperLog(message = "查询所有现金账户",operation = OperationType.QUERY)
    public Map<String,Object> selectAccount(String page, String limit,String accountName,String blankName){
        //调用Service层执行查询，接收返回结果集Map
        Map<String, Object> map = accountService.selectAccount(limit, page,accountName,blankName);
        //从结果集中拿出结果
        List<Account> accountList= (List<Account>) map.get("accountList");
        int count= (int) map.get("count");
        //以layui要求存储响应数据格式
        Map<String,Object> json=new HashMap<>();
        json.put("code",0);
        json.put("msg","");
        json.put("count",count);
        json.put("data",accountList);
        //返回数据
        return json;
    }

    /**
     * 界面请求的信息数据控制层增加现金账户的方法
     * @param account 现金账户实体类
     * @return 返回影响的行数 判断是否增加成功
     */
    @RequestMapping("insertAccount")
    @OperLog(message = "增加现金账户",operation = OperationType.ADD)
    public int insertAccount(Account account){
        account.setAccountId(dbUtil.requestDbTableMaxId(SysTableNameListUtil.A));
        int i = accountService.insertAccount(account);
        return i;
    }

    /**
     * 界面请求的信息数据控制层删除现金账户的方法
     * @param accountId 现金账户的Id
     * @return 返回影响的行数 判断是否删除成功
     */
    @RequestMapping("deleteAccount")
    @OperLog(message = "删除现金账户",operation = OperationType.DELETE)
    public int deleteAccount(String accountId){
        System.out.println(accountId);
        int i = accountService.deleteAccount(accountId);
        return i;
    }

    /**
     * 界面请求的信息数据控制层修改现金账户的方法
     * @param account 现金账户实体类
     * @return 返回影响的行数 判断是否修改成功
     */
    @RequestMapping("updateAccount")
    @OperLog(message = "修改现金账户",operation = OperationType.UPDATE)
    public int updateAccount(Account account){
        System.out.println(account);
        return accountService.updateAccount(account);
    }
}



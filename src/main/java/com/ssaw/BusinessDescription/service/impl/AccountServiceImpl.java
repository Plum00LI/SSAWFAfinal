package com.ssaw.BusinessDescription.service.impl;

import com.ssaw.BusinessDescription.entity.Account;
import com.ssaw.BusinessDescription.mapper.AccountMapper;
import com.ssaw.BusinessDescription.service.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*@program:TescComment
*@Eescription:现金账户
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    /**
     * 注入Account的Mapper层
     */
    @Resource
    AccountMapper accountMapper;

    /**
     * 查询所有现金账户的实现类方法（带分页，返回数据和总条目数）
     * @param pageSize 当前查询页数
     * @param page 分页数据条目数
     * @param accountName 现金账户名称
     * @param blankName 银行名称
     * @return 查询的结果集Map
     */
    @Override
    public Map<String, Object> selectAccount(String pageSize, String page,String accountName,String blankName) {
        //创建一个结果集Map用于存放两个结果变量
        Map<String,Object> resultMap=new HashMap<>();
        //定义一个分页条数变量
        int v_pageSize=0;
        //判断传入的pageSize是否为空/null
        if (pageSize!=null && !pageSize.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_pageSize=Integer.parseInt(pageSize);
        }
        //定义一个分页页码变量
        int v_page = 0;
        //判断传入的page是否为null/空
        if (page!=null&&!page.equals("")){
            //通过Integer包装类将String类型转换成int基础数据类型
            v_page=Integer.parseInt(page);
        }
        String sql="";
        if(accountName!=null && !accountName.equals("")){
            sql=sql+" and accountName like '%"+accountName+"%'";
        }
        if(blankName!=null && !blankName.equals("")){
            sql=sql+" and blankName='"+blankName+"'";
        }
        System.out.println("blankName="+blankName);
        //创建一个Map 用于存款过程的调用传值
        Map<String,Object> map=new HashMap<>();
        //传入存储过程需要查询的表名
        map.put("p_tableName","account");

        System.out.println(sql);
        //传入查询的条件
        map.put("p_condition",sql);
        //传入分页显示条数
        map.put("p_pageSize",v_pageSize);
        //传入分页页码
        map.put("p_page",v_page);
        //创建out参数，返回数据总条数
        map.put("p_count",0);
        //创建out游标变量，返回查询数据
        map.put("p_cursor",null);
        //调用Mapper执行查询
        accountMapper.selectAccount(map);
        //接收返回数据
        List<Account> accountList= (List<Account>) map.get("p_cursor");

        //接收返回总条数
        int v_count= (int) map.get("p_count");
        //将结果放入结果集Map
        resultMap.put("accountList",accountList);
        resultMap.put("count",v_count);
        System.out.println(resultMap.get("accountList"));
        return resultMap;
    }

    /**
     * 现金账户的增加实现类方法
     * @param account 现金账户的实体类
     * @return
     */
    @Override
    public int insertAccount(Account account) {
        return accountMapper.insertAccount(account);
    }

    /**
     * 现金账户的修改实现类方法
     * @param account 现金账户的实体类
     * @return
     */
    @Override
    public int updateAccount(Account account) {
        return accountMapper.updateAccount(account);
    }

    /**
     * 现金账户的删除实现类方法
     * @param accountId 现金账户Id
     * @return
     */
    @Override
    public int deleteAccount(String accountId) {
        String[] split = accountId.split(",");
        ArrayList<Object> accountList = new ArrayList<>();
        for (String id : split) {
            accountList.add(id);
        }
        return accountMapper.deleteAccount(accountList);
    }

    /**
     * 通过ID查询现金账户名和账户卡号的实现类方法
     * @param accountId 现金账户Id
     * @return
     */
    @Override
    public Account selectAccountById(String accountId) {
        return accountMapper.selectAccountById(accountId);
    }
}

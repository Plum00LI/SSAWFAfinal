package com.ssaw.BusinessDescription.service;

import com.ssaw.BusinessDescription.entity.Account;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
*@program:TescComment
*@Eescription:现金账户Service
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
@Service
@Transactional
public interface AccountService {
    //查询所有现金账户的服务类接口方法-待实现
    public Map<String,Object> selectAccount(String pageSize,String page,String accountName,String blankName);
    //增加现金账户的方法
    public int insertAccount(Account account);
    //修改现金账户的方法
    public int updateAccount(Account account);
    //通过账户Id删除数据的方法
    public int deleteAccount(String accountId);
    //通过ID查询现金账户名和账户卡号
    public Account selectAccountById(String accountId);
}

package com.ssaw.BusinessDescription.mapper;

import com.ssaw.BusinessDescription.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


/**
*@program:TescComment
*@Eescription:现金账户的增删改查
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
@Mapper
public interface AccountMapper {
    //查询所有现金账户的方法
    public void selectAccount(Map map);
    //增加现金账户的方法
    public int insertAccount(Account account);
    //修改现金账户的方法
    public int updateAccount(Account account);
    //通过Id删除现金账户的方法
    public int deleteAccount(List accountId);
    //通过ID查询现金账户名和账户卡号
    public Account selectAccountById(String accountId);
}

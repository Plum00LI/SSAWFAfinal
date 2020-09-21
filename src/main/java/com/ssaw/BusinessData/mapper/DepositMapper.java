package com.ssaw.BusinessData.mapper;

import com.ssaw.BusinessData.entity.Deposit;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

/**
*@program:TescComment
*@Eescription:存款业务的增删改查
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
@Mapper
public interface DepositMapper {
    //查询所有存款业务的方法
    public void selectDeposit(Map map);
    //增加存款业务的方法
    public int insertDeposit(Deposit deposit);
    //修改存款业务的方法
    public int updateDeposit(Deposit deposit);
    //通过存款业务Id删除的方法
    public int deleteDeposit(String depositId);
}

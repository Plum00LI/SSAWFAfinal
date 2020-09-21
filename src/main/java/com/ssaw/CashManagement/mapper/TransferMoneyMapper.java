package com.ssaw.CashManagement.mapper;


import com.ssaw.CashManagement.entity.TransferMoney;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
*@program:TescComment
*@Eescription:划款指令增删改查
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
@Mapper
public interface TransferMoneyMapper {
    //查询所有划款指令的方法
    public void selectTransferMoney(Map map);
    //增加划款指令的方法
    public int insertTransferMoney(TransferMoney transferMoney);
    public int updateTransferMoney(TransferMoney transferMoney);
    //通过划款指令Id删除的方法
    public int deleteTransferMoney(List transferMoneyId);
    //通过ID查询划款指令
    public TransferMoney selectTransferMoneyByTransferMoneyId(String transferMoneyId);
}

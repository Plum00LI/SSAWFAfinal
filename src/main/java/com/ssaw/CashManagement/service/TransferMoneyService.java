package com.ssaw.CashManagement.service;

import com.ssaw.CashManagement.entity.TransferMoney;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
*@program:TescComment
*@Eescription:划款指令
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
@Service
public interface TransferMoneyService {
    //查询所有划款指令的方法
    public Map<String,Object> selectTransferMoney(String pageSize,String page,String crossSectionDate);
    //增加划款指令的方法
    public int insertTransferMoney(TransferMoney transferMoney);
    public int updateTransferMoney(TransferMoney transferMoney);
    //通过划款指令Id删除的方法
    public int deleteTransferMoney(String transferMoneyId);
}

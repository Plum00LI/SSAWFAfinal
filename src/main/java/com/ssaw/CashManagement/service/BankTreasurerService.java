package com.ssaw.CashManagement.service;

import com.ssaw.CashManagement.entity.BankTreasurer;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
*@program:TescComment
*@Eescription:资金调拨service
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
@Service
public interface BankTreasurerService {
    //查询所有资金调拨的方法
    public Map<String,Object> selectBankTreasurer(String pageSize,String page,String allocatingType,String flag,String dbTime);
    //增加资金调拨的方法
    public int insertBankTreasurer(BankTreasurer bankTreasurer);
    //修改资金调拨的方法
    public int updateBankTreasurer(BankTreasurer bankTreasurer);
    //通过资金调拨Id删除资金调拨的方法
    public int deleteBankTreasurer(String bankTreasurerId);
    //通过存款业务Id删除资金调拨的方法
    public int deleteBankTreasurerByDepositId(String depositId);
}

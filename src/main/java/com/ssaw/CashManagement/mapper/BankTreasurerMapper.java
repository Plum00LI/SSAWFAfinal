package com.ssaw.CashManagement.mapper;

import com.ssaw.CashManagement.entity.BankTreasurer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
*@program:TescComment
*@Eescription:资金调拨的增删改查
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
@Mapper
public interface BankTreasurerMapper {
    //查询所有资金调拨的方法
    public void selectBankTreasurer(Map map);
    //增加资金调拨的方法
    public int insertBankTreasurer(BankTreasurer bankTreasurer);
    //修改资金调拨的方法
    public int updateBankTreasurer(BankTreasurer bankTreasurer);
    //通过资金调拨Id删除资金调拨的方法
    public int deleteBankTreasurer(List bankTreasurerId);
    //通过存款业务Id删除资金调拨的方法
    public int deleteBankTreasurerByDepositId(String depositId);
}

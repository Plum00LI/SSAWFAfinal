package com.ssaw.BusinessData.service;

import com.ssaw.BusinessData.entity.Deposit;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
*@program:TescComment
*@Eescription:存款业务service
*@author:黄庆
*@Version:1.0
*@create:2020-09-01
*/
@Service
@Transactional
public interface DepositService {
    //查询所有存款业务的服务类接口方法
    public Map<String,Object> selectDeposit(String pageSize,String page,String businessType,String endDate);
    //增加存款业务的方法
    public int insertDeposit(Deposit deposit);
    //修改存款业务的方法
    public int updateDeposit(Deposit deposit);
    //通过存款业务Id删除的方法
    public int deleteDeposit(String depositId);
}

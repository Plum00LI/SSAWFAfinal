package com.ssaw.TAManagement.service;

import com.ssaw.TAManagement.entity.TaTransaction;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * TA交易数据服务层接口
 * @type:service_interface
 * @version:v1.0
 * @authod:洪彬峰
 * @date:2020-09-01
 */
@Service

public interface TatransactionService {
    //查询
    Map<String,Object> selectTatransaction(String pageSize, String page,String dateTime,String transactionStatus,String transactionType);
    //增加
    public int insertTatransaction(TaTransaction tatransaction);
    //删除
    public int  deleteTatransaction(String taTransactionId);
    //修改
    public int updataTetransaction(TaTransaction tatransaction);
}

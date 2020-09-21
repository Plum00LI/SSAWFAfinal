package com.ssaw.BusinessData.service;

import com.ssaw.BusinessData.entity.TransactionData;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * create by: 曾钦辉
 * description: 交易数据Service接口
 * create time: 2020/9/1 11:20
 *
  * @Param: null
 * @return
 */
@Service
public interface TransactionDataService {
    /**
     * 增加交易数据
     * @param transactionData
     * @return int
     */
    int insertTransactionData(TransactionData transactionData);

    /**
     * 删除交易数据
     * @param transactionDataId
     */
    String deleteTransactionData(String transactionData, String transactionDataId);

    /**
     *  修改交易数据
     * @param transactionData
     * @return int
     */
    int updateTransactionData(TransactionData transactionData);

    /**
     * 查询交易数据
     * @return List<TransactionData>
     */
    HashMap selectTransactionData(int page,int limit,String dateTime,String securitiesName);
}

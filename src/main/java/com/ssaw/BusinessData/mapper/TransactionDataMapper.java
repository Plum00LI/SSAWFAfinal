package com.ssaw.BusinessData.mapper;

import com.ssaw.BusinessData.entity.TransactionData;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

/**
 * create by: 曾钦辉
 * description: 交易数据mapper接口
 * create time: 2020/9/1 11:12
 *
  * @Param: null
 * @return
 */
@Mapper
public interface TransactionDataMapper {
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
    void deleteTransactionData(String transactionDataId);

    /**
     *  修改交易数据
     * @param transactionData
     * @return int
     */
    int updateTransactionData(TransactionData transactionData);

    /**
     * 查询交易数据
     * @return
     */
     void selectTransactionData(HashMap hashMap);
}

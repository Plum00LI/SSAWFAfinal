package com.ssaw.TAManagement.mapper;

import com.ssaw.TAManagement.entity.TaTransaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * TA交易数据dao层接口
 * @type:Mapper_interface
 * @version:v1.0
 * @authod:洪彬峰
 * @date:2020-09-01
 */

@Mapper
public interface TaTransactionMapper {

    /**
     * 查询基金信息
     * @param
     * @return List<Fund>
     */
     void selectTaTransaction(Map map);
    /**
     * 增加基金信息
     * @param tatransaction
     * @return int
     */
     int insertTaTransaction(TaTransaction tatransaction);
    /**
     * 删除基金信息
     * @param taTransactionId
     */
     int  deleteTaTransaction(List taTransactionId);
    /**
     * 修改基金信息
     * @param tatransaction
     * @return
     */
     int updateTaTransaction(TaTransaction tatransaction);
}

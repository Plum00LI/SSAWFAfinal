package com.ssaw.BusinessProcessing.service;

import java.util.Map;

/**
 * @program:TescComment
 * @Description:实体类
 * @authod:洪彬峰
 * @create:2020-09-01
 */
public interface TaSettlementService {
    /**
     * 查询
     */
    Map<String,Object> selectTaSettlement(String pageSize, String page, String dateTime,String transactionType,String status);

    /*
    修改
     */
    public int updateSettlement(String taSettlement);
    public int updateSettlementTwo(String taSettlement);

}

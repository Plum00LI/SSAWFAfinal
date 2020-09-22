package com.ssaw.ReportManagement.service;

import com.ssaw.DayEndProcessing.entity.ValueStatistics;
import com.ssaw.GlobalManagement.log.OperLog;
import com.ssaw.GlobalManagement.util.OperationType;
import com.ssaw.ReportManagement.entity.FundInvestPlateTable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * create by: 佘高鹏
 * description: TODO
 * 基金投资板块报表业务层
 * create time: 2020/9/21 11:28
 * version number 1.0
  * @Param: null
 * @return
 */
@Service
public interface FundInvestPlateTableService {
    /**
     * 证券板块查询方法
     * @param valueStatisticsDate 时间
     * @param valueStatisticsDateTwo 行情时间
     * @param fundId  基金ID
     * @return
     */
    public List<FundInvestPlateTable> selectFundInvestPlate(String valueStatisticsDate,String fundId,String valueStatisticsDateTwo);

    /**
     * 资产净值查询方法
     * @param valueStatisticsDate 时间
     * @param fundId 基金ID
     * @return
     */
    public ValueStatistics selectValue(String valueStatisticsDate, String fundId);
}
